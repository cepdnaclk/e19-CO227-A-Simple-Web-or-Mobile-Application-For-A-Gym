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

/**
 * Represents a trainee user in the application, extending the AppUser class.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Trainee extends AppUser {

    // The training program associated with the trainee.
    private String trainingProgram;

    // The trainer assigned to the trainee.
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
