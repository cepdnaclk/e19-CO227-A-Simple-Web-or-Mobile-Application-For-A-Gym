package com.e19co227.gymhub.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    private final String userName;
    private final String fullName;
    private final String email;
    private final String password;
    private String role;
    private String contactNumber;
    private String nic;

//    public String getRole() {
//        return role;
//    }
}

