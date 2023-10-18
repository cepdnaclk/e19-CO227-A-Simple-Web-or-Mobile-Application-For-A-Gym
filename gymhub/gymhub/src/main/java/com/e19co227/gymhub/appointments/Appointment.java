
// Define the package for the Appointments component of the application
package com.e19co227.gymhub.appointments;

// Import necessary dependencies and classes
import com.e19co227.gymhub.appuser.AppUser;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

// Annotate the class with Lombok annotations for generating boilerplate code
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table

// Define the 'Appointment' entity class
public class Appointment {

    // Specify the primary key field for this entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    // Define a many-to-one relationship with a trainer (AppUser entity)
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @Hidden  // Mark this field as hidden in Swagger documentation
    private AppUser trainer;

    // Define a many-to-one relationship with a trainee (AppUser entity)
    @ManyToOne
    @JoinColumn(name="trainee_id")
    @Hidden  // Mark this field as hidden in Swagger documentation
    private AppUser trainee;

    // Define fields for the appointment details
    private Date date;  // Date of the appointment
    private Time startTime;  // Start time of the appointment
    private Time endTime;  // End time of the appointment

}

