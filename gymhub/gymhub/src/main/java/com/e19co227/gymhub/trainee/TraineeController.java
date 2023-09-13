package com.e19co227.gymhub.trainee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("trainee")
public class TraineeController {

    @Autowired
    TraineeService traineeService;
    @GetMapping("allTrainees")
    public ResponseEntity<List<Trainee>> getAllTrainees() {

        return traineeService.getAllTrainee();
    }

    @PostMapping("register")
    public ResponseEntity<String> registerTrainee(@RequestBody Trainee trainee){
        return traineeService.registerTrainee(trainee);
    }
}
