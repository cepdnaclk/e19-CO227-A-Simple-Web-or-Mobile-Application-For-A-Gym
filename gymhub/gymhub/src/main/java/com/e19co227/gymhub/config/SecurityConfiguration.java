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

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
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

                .requestMatchers("/api/v1/trainer/**").hasRole(TRAINER.name())

                .requestMatchers(GET, "/api/v1/trainer/**").
                hasAuthority(TRAINER_READ.name())
                .requestMatchers(POST, "/api/v1/trainer/**").
                hasAuthority(TRAINER_CREATE.name())
                .requestMatchers(PUT, "/api/v1/trainer/**").
                hasAuthority(TRAINER_UPDATE.name())
                .requestMatchers(DELETE, "/api/v1/trainer/**").
                hasAuthority(TRAINER_DELETE.name())

                .requestMatchers("/api/v1/trainee/**").hasRole(TRAINEE.name())

                .requestMatchers(GET, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_READ.name())
                .requestMatchers(POST, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_CREATE.name())
                .requestMatchers(PUT, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_UPDATE.name())
                .requestMatchers(DELETE, "/api/v1/trainee/**").
                hasAuthority(TRAINEE_DELETE.name())

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
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
}
