package com.e19co227.gymhub.trainee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("trainees")
public class TraineeController {

    @GetMapping
    public List<Trainee> getAllTrainees() {

        return List.of(
             new Trainee(UUID.randomUUID(),
                     "w1123",
                     "Kamal",
                     Trainee.Gender.Male,
                     80,
                     "Kamal@gmail.com",
                     "0112345678",
                     "586749375v",
                     "w1@123",
                     45)
        );
    }
}
