package com.e19co227.gymhub.appuser;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {



    @SequenceGenerator(
            name = "appUser_sequence",
            sequenceName = "appUser_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appUser_sequence"
    )
    private Long id;
    private String userName;
    private String phoneNumber;
    private String name;
    private String email;
    private String password;
    private String nic;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(String userName,
                   String phoneNumber,
                   String email,
                   String password,
                   String nic,
                   String name,
                   AppUserRole appUserRole) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.nic = nic;
        this.name = name;
        this.appUserRole = appUserRole;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNic() {
        return nic;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }


    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /*@OneToMany(mappedBy = "appUser")
    private Collection<ConfirmationToken> app_user_id;

    public Collection<ConfirmationToken> getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(Collection<ConfirmationToken> app_user_id) {
        this.app_user_id = app_user_id;
    }*/
}