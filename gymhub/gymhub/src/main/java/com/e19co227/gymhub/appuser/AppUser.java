package com.e19co227.gymhub.appuser;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private String fullName;

    //@Column(name = "contact_number")
    private String contactNumber;
    private String nic;
    private String address1;
    private String address2;
    private String address3;
    private Boolean enabled = false;

/*    @OneToMany(mappedBy = "user")
    private List<Token> tokens = new ArrayList<>();*/


    public AppUser(
            String userName,
            String email,
            String password,
            AppUserRole appUserRole,
            String fullName,
            String contactNumber,
            String nic) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.nic = nic;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUserRole.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }


     @Override
     public String getPassword() {
         return password;
     }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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

