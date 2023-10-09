package com.e19co227.gymhub.trainee;

import com.e19co227.gymhub.appuser.AppUser;
import com.e19co227.gymhub.trainer.Trainer;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Trainee extends AppUser {

    private String trainingProgram;


    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
