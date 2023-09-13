package com.e19co227.gymhub.appointments;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
}
