package com.e19co227.gymhub.config;

import com.e19co227.gymhub.appuser.AppUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    // Inject the AppUserDao dependency using constructor injection.
    private final AppUserDao appUserDao;

    // Bean to provide a custom implementation of UserDetailsService.
    @Bean
    public UserDetailsService userDetailsService(){

        // Lambda expression to find a user by their email address.
        return username -> appUserDao.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }

    // Bean to configure and provide a custom AuthenticationProvider.
    @Bean
    public AuthenticationProvider authenticationProvider() {

        // Create a DaoAuthenticationProvider and configure it with custom UserDetailsService and PasswordEncoder.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Bean to provide a custom AuthenticationManager.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Bean to provide a BCryptPasswordEncoder for password encoding.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
