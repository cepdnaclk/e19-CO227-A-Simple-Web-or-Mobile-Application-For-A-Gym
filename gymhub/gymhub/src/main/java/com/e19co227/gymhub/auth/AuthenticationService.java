package com.e19co227.gymhub.auth;

// Import necessary dependencies and classes
import com.e19co227.gymhub.appuser.AppUser;
import com.e19co227.gymhub.appuser.AppUserDao;
import com.e19co227.gymhub.appuser.AppUserRole;
import com.e19co227.gymhub.config.JwtService;
import com.e19co227.gymhub.email.EmailSender;
import com.e19co227.gymhub.registration.AppUserService;
import com.e19co227.gymhub.registration.EmailValidator;
import com.e19co227.gymhub.registration.token.ConfirmationToken;
import com.e19co227.gymhub.registration.token.ConfirmationTokenService;
import com.e19co227.gymhub.token.Token;
import com.e19co227.gymhub.token.TokenDao;
import com.e19co227.gymhub.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

// Define an authentication service class for handling authentication-related operations
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    // Inject dependencies using constructor injection
    private final AppUserDao appUserDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserService appUserService;
    private final EmailSender emailSender;
    private final EmailValidator emailValidator;
    private final TokenDao tokenDao;

    // Register a new user based on the provided registration request
    public AuthenticationResponse register(RegisterRequest request) {

        // Validate the email
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail){
            throw new IllegalStateException("Email not valid ");
        }

        // Build a new AppUser object
        var appUser = AppUser.builder()
                .userName(request.getUserName())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .appUserRole(AppUserRole.valueOf(request.getAppUserRole().toUpperCase()))
                .nic(request.getNic())
                .build();

        // Check if the email is already taken
        boolean userExists = appUserDao.
                findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists){
            throw new IllegalStateException("Email already taken");
        }

        // Save the user in the database
        var savedUser = appUserDao.save(appUser);

        // Generate a confirmation token and send an email with a confirmation link
        String token = appUserService.signUpUser(appUser);
        String link = "http://localhost:8080/api/v1/auth/confirm?token="+token;
        emailSender.send(
                request.getEmail(),
                buildEmail(request.getFullName(), link));

        // Generate an access token and a refresh token for the registered user
        var jwtToken = jwtService.generateToken(appUser);
        var refreshToken = jwtService.generateRefreshToken(appUser);
        saveUserToken(savedUser, jwtToken);

        // Return the authentication response
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .appUserRole(AppUserRole.valueOf(request.getAppUserRole().toUpperCase()))
                .build();
    }

    // Authenticate a user based on the provided authentication request
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Find the user by email
        var appUser = appUserDao.findByEmail(request.getEmail())
                .orElseThrow();

        // Generate a new access token and refresh token for the authenticated user
        var jwtToken = jwtService.generateToken(appUser);
        var refreshToken = jwtService.generateRefreshToken(appUser);

        // Revoke all previous user tokens and save the new access token
        revokeAllUserTokens(appUser);
        saveUserToken(appUser, jwtToken);

        // Return the authentication response
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .appUserRole(appUser.getAppUserRole())
                .build();
    }

    // Confirm a token by checking its validity and expiration
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

    // Build an email with a confirmation link
    private String buildEmail(String name, String link) {

        // Email HTML content
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


    private void saveUserToken(AppUser appUser, String jwtToken) {

        // Create a new Token object with the provided user and JWT token.
        var token = Token.builder()
                .appUser(appUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        // Save the token in the database.
        tokenDao.save(token);
    }
    private void revokeAllUserTokens(AppUser appUser) {

        // Retrieve all valid tokens associated with the given user.
        var validUserTokens = tokenDao.findAllValidTokenByUser(appUser.getUserId());

        // If no valid tokens are found, return without further action.
        if (validUserTokens.isEmpty())
            return;

        // Set all valid tokens as expired and revoked.
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        // Save the updated token statuses in the database.
        tokenDao.saveAll(validUserTokens);
    }
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        // Extract the Authorization header from the HTTP request.
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        // Check if the Authorization header is present and starts with "Bearer ".
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;  // If not, exit the method.
        }

        // Extract the refresh token and user email from the Authorization header.
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {

            // Find the user associated with the extracted email.
            var user = this.appUserDao.findByEmail(userEmail)
                    .orElseThrow();  // Throw an exception if the user is not found.

            // Check if the refresh token is valid for the user.
            if (jwtService.isTokenValid(refreshToken, user)) {

                // Generate a new access token for the user.
                var accessToken = jwtService.generateToken(user);

                // Revoke all the user's tokens by marking them as expired and revoked.
                revokeAllUserTokens(user);

                // Save the new access token in the database.
                saveUserToken(user, accessToken);

                // Create an authentication response containing the new access token and the original refresh token.
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                // Write the authentication response to the HTTP response's output stream.
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


}
