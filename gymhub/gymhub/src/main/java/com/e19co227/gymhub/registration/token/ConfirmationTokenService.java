
package com.e19co227.gymhub.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    // Inject ConfirmationTokenDao dependency using constructor injection.
    private final ConfirmationTokenDao confirmationTokenDao;

    // Save a confirmation token to the database.
    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenDao.save(token);
    }

    // Retrieve a confirmation token by its token value.
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenDao.findByToken(token);
    }

    // Set the "confirmedAt" timestamp when a token is confirmed during email verification.
    public int setConfirmedAt(String token) {
        return confirmationTokenDao.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}

