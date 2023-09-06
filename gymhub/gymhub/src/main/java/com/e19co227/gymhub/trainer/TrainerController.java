package com.e19co227.gymhub.trainer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("trainers")
public class TrainerController {

    @GetMapping
    public List<Trainer> getAllTrainers() {

        return List.of(
                new Trainer(UUID.randomUUID(),
                        "w123",
                        "Wishula",
                        Trainer.Gender.Female,
                        55,
                        "w@gmail.com",
                        "0713816847",
                        "996646856v",
                        "abcd",
                        23
                        )
        );
    }
}
