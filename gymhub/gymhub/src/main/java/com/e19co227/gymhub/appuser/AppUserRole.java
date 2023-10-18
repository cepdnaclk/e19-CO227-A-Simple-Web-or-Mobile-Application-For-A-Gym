package com.e19co227.gymhub.appuser;

// Import necessary dependencies and classes
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.e19co227.gymhub.appuser.Permission.*;

// Define an enum representing different roles for application users
@RequiredArgsConstructor
public enum AppUserRole {

    // Define two user roles: TRAINER and TRAINEE
    TRAINER(
            Set.of(
                    TRAINER_READ,
                    TRAINER_UPDATE,
                    TRAINER_DELETE,
                    TRAINER_CREATE,
                    TRAINEE_READ
            )
    ),
    TRAINEE(
            Set.of(
                    TRAINEE_READ,
                    TRAINEE_UPDATE,
                    TRAINEE_DELETE,
                    TRAINEE_CREATE,
                    TRAINER_READ
            )
    );

    // Define a getter for the set of permissions associated with each role
    @Getter
    private final Set<Permission> permissions;

    // Define a method to convert permissions into SimpleGrantedAuthority objects
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        // Add a role-specific authority based on the role's name
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
