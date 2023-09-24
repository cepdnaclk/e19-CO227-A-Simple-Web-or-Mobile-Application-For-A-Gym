package com.e19co227.gymhub.timeslot;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeSlotId;
    private Integer trainerId;
    private String trainerUsername;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
