package com.e19co227.gymhub.appointments;


import com.e19co227.gymhub.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

    List<Appointment> getAppointmentsByTrainee(AppUser trainee);
    List<Appointment> getAppointmentsByTrainer(AppUser trainer);
}
