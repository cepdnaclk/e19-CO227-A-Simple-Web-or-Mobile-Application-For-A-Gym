package com.e19co227.gymhub.appointments;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;
    private Integer trainerId;
    private Integer traineeId;
    private Date date;
    private Time startTime;
    private Time endTime;

}
