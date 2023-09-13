package com.e19co227.gymhub.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("trainer")
public class TrainerController {

    @Autowired
    TrainerService trainerService;
    @GetMapping("allTrainers")
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        return trainerService.getAllTrainer();
    }


    @PostMapping("register")
    public ResponseEntity<String> registerTrainer(@RequestBody Trainer trainer){
        return trainerService.registerTrainer(trainer);
    }
}
