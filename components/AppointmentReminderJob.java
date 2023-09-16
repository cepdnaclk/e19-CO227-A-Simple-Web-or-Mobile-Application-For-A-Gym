import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class GymAppointmentReminder {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail job = JobBuilder.newJob(AppointmentReminderJob.class)
                    .withIdentity("appointmentReminderJob", "group")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("appointmentReminderTrigger", "group")
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))  // Run every day at midnight
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static class AppointmentReminderJob implements Job {
        // Method to retrieve the list of gym members with appointments for the next day
        private List<Member> getMembersWithNextDayAppointments() {
            List<Member> membersWithAppointments = new ArrayList<>();

            // Example: Fetch members with appointments from a database
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                // Create a database connection
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gym", "root", "");

                // Calculate the date for the next day
                Date nextDay = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

                // Execute a query to fetch members with appointments for the next day
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT name, email, appointment_date " +
                        "FROM members WHERE appointment_date = ?");

                // Iterate over the result set and create Member objects
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date appointmentDate = resultSet.getDate("appointment_date");

                    // Check if the appointment date is the next day
                    if (appointmentDate.equals(nextDay)) {
                        Member member = new Member(name, email);
                        membersWithAppointments.add(member);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Close the database resources
                // ...
            }

            return membersWithAppointments;
        }

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            // Logic to send appointment reminders for the next day
            try {
                // Get the list of gym members with appointments for the next day
                List<Member> members = getMembersWithNextDayAppointments();

                // Iterate over each member and send appointment reminders
                for (Member member : members) {
                    sendAppointmentReminderEmail(member.getEmail(), member.getName());
                }
            } catch (MessagingException e) {
                // Handle any exceptions or errors that occur during the process
                throw new JobExecutionException(e);
            }
        }

        private void sendAppointmentReminderEmail(String recipientEmail, String memberName) throws MessagingException {
            // Configure the email server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.example.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");

            // Create a Session with authentication credentials
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("e19424@eng.pdn.ac.lk", "E9A0987RABCE123DF5");
                }
            });

            try {
                // Create a new email message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("your_email"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject("Gym Appointment Reminder");
                message.setText("Dear " + memberName + ",\n\nThis is a reminder of your gym appointment tomorrow.\n\nPlease be prepared and arrive on time.\n\nBest regards,\nThe Gym Team");

                // Send the email
                Transport.send(message);
            } catch (MessagingException e) {
                // Handle any exceptions or errors that occur during the process
                throw e;
            }
        }
    }

    public static class Member {
        private String name;
        private String email;

        public Member(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
