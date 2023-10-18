package com.e19co227.gymhub.config;

import com.e19co227.gymhub.token.TokenDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    // Inject TokenDao dependency using constructor injection.
    private final TokenDao tokenDao;

    // Implementation of the logout logic when a user logs out of the application.
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {

        // Extract the JWT token from the Authorization header.
        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        // Check if the Authorization header is present and starts with "Bearer ".
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;  // If not, exit the method.
        }

        // Extract the JWT token from the Authorization header.
        jwt = authHeader.substring(7);

        // Find the stored token in the database based on the extracted token.
        var storedToken = tokenDao.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {

            // If the token is found, mark it as expired and revoked.
            storedToken.setExpired(true);
            storedToken.setRevoked(true);

            // Save the updated token status in the database.
            tokenDao.save(storedToken);

            // Clear the security context, effectively logging the user out.
            SecurityContextHolder.clearContext();
        }
    }
}