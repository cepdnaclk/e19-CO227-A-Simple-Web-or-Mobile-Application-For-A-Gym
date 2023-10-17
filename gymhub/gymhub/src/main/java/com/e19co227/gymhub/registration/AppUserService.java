package com.e19co227.gymhub.registration;

import com.e19co227.gymhub.appuser.AppUser;
import com.e19co227.gymhub.appuser.AppUserDao;
import com.e19co227.gymhub.registration.token.ConfirmationToken;
import com.e19co227.gymhub.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class  AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "username with email %s not found";

    // Dependencies injected using constructor injection.
    private final AppUserDao appUserDao;  // Data access for AppUser entities.
    private final ConfirmationTokenService confirmationTokenService;  // Service for managing confirmation tokens.

    public ResponseEntity<List<AppUser>> getAllUsers() {

        // Retrieve all users and return them in a ResponseEntity with OK status.
        return new ResponseEntity<>(appUserDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> registerUser(AppUser appUser) {

        // Save the user to the database.
        appUserDao.save(appUser);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Load a user by their email (username) and return UserDetails.
        return appUserDao.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser (AppUser appUser){

        // Generate a random token for email confirmation.
        String token = UUID.randomUUID().toString();

        // Create a confirmation token with an expiration time.
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        // Save the confirmation token to the database.
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableAppUser(String email) {

        // Enable the app user (usually after email confirmation).
        appUserDao.enableAppUser(email);
    }
}