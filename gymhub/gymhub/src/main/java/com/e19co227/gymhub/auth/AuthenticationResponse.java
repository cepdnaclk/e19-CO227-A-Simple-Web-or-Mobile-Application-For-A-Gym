package com.e19co227.gymhub.auth;

// Import necessary dependencies and classes
import com.e19co227.gymhub.appuser.AppUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Define a class for representing an authentication response
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    // Define fields for the access token, refresh token, and user role
    @JsonProperty("access_token")
    private String accessToken;  // The access token used for authentication
    @JsonProperty("refresh_token")
    private String refreshToken;  // The refresh token used for token renewal
    @JsonProperty("role")
    private AppUserRole appUserRole;  // The user's role, as an AppUserRole enum
}
