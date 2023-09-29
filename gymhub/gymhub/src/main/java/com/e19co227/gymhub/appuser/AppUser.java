package com.e19co227.gymhub.appuser;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class AppUser implements UserDetails {


    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Integer userId;

    //@Column(name = "user_name",nullable = false)
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private String fullName;
    private String contactNumber;
    private String nic;
    private String address1;
    private String address2;
    private String address3;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(String userName,
                   String email,
                   String password,
                   String role,
                   String fullName,
                   String contactNumber,
                   String nic
                   ) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUserRole = AppUserRole.valueOf(role.toUpperCase());
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.nic = nic;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //AppUserRole appUserRole = AppUserRole.valueOf(role.toUpperCase());
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return userName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

