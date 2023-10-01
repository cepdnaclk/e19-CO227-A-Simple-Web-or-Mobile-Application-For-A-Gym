package com.e19co227.gymhub.timeslot;

import com.e19co227.gymhub.appuser.AppUser;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeSlotId;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @Hidden
    private AppUser trainer;
    @ManyToOne
    @JoinColumn(name="trainee_id")
    @Hidden
    private AppUser trainee;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
