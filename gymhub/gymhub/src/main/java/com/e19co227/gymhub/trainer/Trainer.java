package com.e19co227.gymhub.trainer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainerId;
    private String trainerName;
    private String email;
    private String contactNumber;
    private String address1;
    private String address2;
    private String address3;
    private String userName;
    private String nic;
    private String password;

}

