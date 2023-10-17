package com.e19co227.gymhub.timeslot;

import com.e19co227.gymhub.appuser.AppUser;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
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
