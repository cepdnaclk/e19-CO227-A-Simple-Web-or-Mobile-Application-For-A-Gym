package com.e19co227.gymhub.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Define a class for representing an authentication request
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;  // The user's email, used for authentication
    String password;  // The user's password, used for authentication

}
