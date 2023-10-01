package com.e19co227.gymhub.appointments;

import com.e19co227.gymhub.appuser.AppUser;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @Hidden
    private AppUser trainer;
    @ManyToOne
    @JoinColumn(name="trainee_id")
    @Hidden
    private AppUser trainee;

    private Date date;
    private Time startTime;
    private Time endTime;

}
