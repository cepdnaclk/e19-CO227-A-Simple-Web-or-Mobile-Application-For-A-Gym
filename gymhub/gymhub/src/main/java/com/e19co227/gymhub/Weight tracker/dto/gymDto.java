package com.example.Gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class gymDto {
    private int gymId;
    private String exerciseType;
    private String weight;
    private String numberOfSets;
    private String numberOfReps;
}
