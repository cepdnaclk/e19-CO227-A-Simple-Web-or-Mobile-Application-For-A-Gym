package com.e19co227.gymhub.appuser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    TRAINER_READ("trainer:read"),
    TRAINER_UPDATE("trainer:update"),
    TRAINER_CREATE("trainer:create"),
    TRAINER_DELETE("trainer:delete"),
    TRAINEE_READ("trainee:read"),
    TRAINEE_UPDATE("trainer:update"),
    TRAINEE_CREATE("trainer:create"),
    TRAINEE_DELETE("trainer:delete")

    ;

    @Getter
    private final String permission;
}