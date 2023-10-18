package com.e19co227.gymhub.appointments;

// Import necessary dependencies and classes
import com.e19co227.gymhub.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Define a DAO (Data Access Object) interface for managing appointments in the database
public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

    // Define a method for retrieving a list of appointments associated with a trainee
    List<Appointment> getAppointmentsByTrainee(AppUser trainee);

    // Define a method for retrieving a list of appointments associated with a trainer
    List<Appointment> getAppointmentsByTrainer(AppUser trainer);
}
