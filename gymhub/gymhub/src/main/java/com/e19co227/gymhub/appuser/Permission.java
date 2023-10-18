package com.e19co227.gymhub.appuser;

// Import necessary dependencies and classes
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Define an enum representing different permissions for application users
@RequiredArgsConstructor
public enum Permission {

    // Define permission constants with associated permission strings
    TRAINER_READ("trainer:read"),
    TRAINER_UPDATE("trainer:update"),
    TRAINER_CREATE("trainer:create"),
    TRAINER_DELETE("trainer:delete"),
    TRAINEE_READ("trainee:read"),
    TRAINEE_UPDATE("trainer:update"),
    TRAINEE_CREATE("trainer:create"),
    TRAINEE_DELETE("trainer:delete");

    // Define a getter for the permission string associated with each constant
    @Getter
    private final String permission;
}