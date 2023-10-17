package com.e19co227.gymhub.trainee;

import com.e19co227.gymhub.appointments.Appointment;
import com.e19co227.gymhub.appointments.AppointmentDao;
import com.e19co227.gymhub.appuser.AppUser;
import com.e19co227.gymhub.appuser.AppUserDao;
import com.e19co227.gymhub.timeslot.TimeSlotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {

    private final AppUserDao appUserDao;
    private final AppointmentDao appointmentDao;
    private final TimeSlotDao timeSlotDao;

    @Autowired
    public TraineeService(
            AppUserDao appUserDao,
            AppointmentDao appointmentDao,
            TimeSlotDao timeSlotDao) {
        this.appUserDao = appUserDao;
        this.appointmentDao = appointmentDao;
        this.timeSlotDao = timeSlotDao;
    }

    // Update the trainee's profile
    public Trainee updateTrainerProfile(int traineeId, Trainee trainee) {
        if (appUserDao.existsById(traineeId)) {
            trainee.setUserId(traineeId);
            return appUserDao.save(trainee);
        }
        return null;
    }

    // Get all appointments for a trainee
    public List<Appointment> getAllAppointmentsForTrainee(int traineeId) {
        AppUser trainee = appUserDao.findByUserId(traineeId).orElse(null);
        if (trainee != null) {
            return appointmentDao.getAppointmentsByTrainee(trainee);
        } else {
            return Collections.emptyList();
        }
    }

    // Add an appointment by a trainee
    public ResponseEntity<String> addAppointmentByTrainee(
            int traineeId,
            Appointment appointment) {
        AppUser trainee = appUserDao.findByUserId(traineeId).orElse(null);
        //Appointment  = timeSlotDao.findByTimeSlotId(timeSlotId);
        if (trainee != null) {
            appointment.setTrainee(trainee);
            appointmentDao.save(appointment);
            return new ResponseEntity<>("Appointment Added", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Trainer Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete an appointment by a trainee
    public boolean deleteAppointment(int traineeId, int appointmentId) {
        AppUser trainee = appUserDao.findByUserId(traineeId).orElse(null);
        Appointment appointment = appointmentDao.findById(appointmentId).orElse(null);
        if (appointment != null && appointment.getTrainee().equals(trainee)) {
            timeSlotDao.deleteById(appointmentId);
            return true;
        }
        return false;
    }

    // Get the username for a trainee
    public String getUserName(int traineeId) {
        Optional<AppUser> optionalAppUser = appUserDao.findByUserId(traineeId);
        if (optionalAppUser.isPresent()){
            AppUser trainee = optionalAppUser.get();
            return trainee.getUsername();
        }
        return "";
    }
}
