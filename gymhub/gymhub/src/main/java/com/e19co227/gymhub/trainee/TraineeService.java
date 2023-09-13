package com.e19co227.gymhub.trainee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {


    @Autowired
    TraineeDao traineeDao;
    public ResponseEntity<List<Trainee>> getAllTrainee(){
        return new ResponseEntity<>(traineeDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> registerTrainee(Trainee trainee){
        //String hashedPassword = bCryptPasswordEncoder.encode(trainee.getPassword());
        //trainee.setPassword(hashedPassword);

        traineeDao.save(trainee);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }


}
