package com.e19co227.gymhub.config;

import com.e19co227.gymhub.token.TokenDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Inject necessary dependencies using constructor injection.
    private final JwtService jwtService;  // Service for JWT operations.
    private final UserDetailsService userDetailsService;  // Custom UserDetailsService.
    private final TokenDao tokenDao;  // Data access for tokens.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Check if the request has an Authorization header starting with "Bearer".
        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            // If not, continue with the filter chain without authentication.
            filterChain.doFilter(request,response);
            return;
        }

        // Extract the JWT token from the Authorization header.
        jwt = authHeader.substring(7);

        // Extract the user's email from the JWT.
        userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ){

            // If the user email is not null and there is no existing authentication context.

            // Load user details from the custom UserDetailsService.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Check if the JWT token is valid and not expired or revoked.
            var isTokenValid = tokenDao.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            if(jwtService.isTokenValid(jwt, userDetails) && isTokenValid){

                // If the token is valid, create an authentication token.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Set authentication details.
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Set the authentication context in SecurityContextHolder.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue with the filter chain.
        filterChain.doFilter(request,response);

    }
}
