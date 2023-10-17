package com.e19co227.gymhub.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.e19co227.gymhub.appuser.AppUserRole.TRAINEE;
import static com.e19co227.gymhub.appuser.AppUserRole.TRAINER;
import static com.e19co227.gymhub.appuser.Permission.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    // Inject necessary dependencies using constructor injection.
    private final JwtAuthenticationFilter jwtAuthFilter;  // Custom JWT authentication filter.
    private final AuthenticationProvider authenticationProvider;  // Custom authentication provider.
    private final LogoutHandler logoutHandler;  // Custom logout handler.


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(

                        // Permit access to specific paths without authentication.
                        "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                )
                .permitAll()

                // Require TRAINER role for requests under "/api/v1/trainer/**".
                .requestMatchers("/api/v1/trainer/**").hasRole(TRAINER.name())

                // Define specific authorities required for various HTTP methods on "/api/v1/trainer/**".
                .requestMatchers(GET, "/api/v1/trainer/**").
                hasAuthority(TRAINER_READ.name())
                .requestMatchers(POST, "/api/v1/trainer/**").
                hasAuthority(TRAINER_CREATE.name())
                .requestMatchers(PUT, "/api/v1/trainer/**").
                hasAuthority(TRAINER_UPDATE.name())
                .requestMatchers(DELETE, "/api/v1/trainer/**").
                hasAuthority(TRAINER_DELETE.name())

                // Require TRAINEE role for requests under "/api/v1/trainee/**".
                .requestMatchers("/api/v1/trainee/**").hasRole(TRAINEE.name())

                // Define specific authorities required for various HTTP methods on "/api/v1/trainee/**".
                .requestMatchers(GET, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_READ.name())
                .requestMatchers(POST, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_CREATE.name())
                .requestMatchers(PUT, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_UPDATE.name())
                .requestMatchers(DELETE, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_DELETE.name())

                // Require authentication for any other request.
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

        return http.build();
    }
}
