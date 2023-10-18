package com.example.Gym.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "gym")

public class gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gymId;
    private String exerciseType;
    private String weight;
    private String numberOfSets;
    private String numberOfReps;
}
