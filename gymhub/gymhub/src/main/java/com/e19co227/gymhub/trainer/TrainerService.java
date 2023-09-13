package com.e19co227.gymhub.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TrainerService {

    @Autowired
    TrainerDao trainerDao;

    public ResponseEntity<List<Trainer>> getAllTrainer() {

        return new ResponseEntity<>(trainerDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> registerTrainer(Trainer trainer) {

        //String hashedPassword = bCryptPasswordEncoder.encode(trainer.getPassword());
        //trainer.setPassword(hashedPassword);

        trainerDao.save(trainer);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);

    }
}
