package com.e19co227.gymhub.auth;

// Import necessary dependencies and classes
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

// Define a REST controller for handling authentication-related operations
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    // Inject the AuthenticationService to handle authentication logic
    private final AuthenticationService service;

    // Define an endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    // Define an endpoint for user authentication
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    // Define an endpoint for confirming a token
    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){

        return service.confirmToken(token);
    }

    // Define an endpoint for refreshing authentication tokens
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

}
