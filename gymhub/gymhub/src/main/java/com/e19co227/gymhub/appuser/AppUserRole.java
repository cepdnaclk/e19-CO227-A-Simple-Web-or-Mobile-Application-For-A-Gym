package com.e19co227.gymhub.appuser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.e19co227.gymhub.appuser.Permission.*;


@RequiredArgsConstructor
public enum AppUserRole {
    //USER(Collections.emptySet()),
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
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
