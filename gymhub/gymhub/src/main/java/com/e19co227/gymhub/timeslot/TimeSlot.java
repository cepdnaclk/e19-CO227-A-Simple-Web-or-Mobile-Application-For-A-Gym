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

    // Unique identifier for the time slot.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeSlotId;

    // Many-to-One relationship with the associated trainer (AppUser entity).
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @Hidden  // Hidden from OpenAPI documentation.
    private AppUser trainer;

    // Many-to-One relationship with the associated trainee (AppUser entity).
    @ManyToOne
    @JoinColumn(name="trainee_id")
    @Hidden  // Hidden from OpenAPI documentation.
    private AppUser trainee;

    // Date of the time slot.
    private LocalDate date;

    // Start time of the time slot.
    private LocalTime startTime;

    // End time of the time slot.
    private LocalTime endTime;
}
