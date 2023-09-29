package com.e19co227.gymhub.appuser;

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

    private final AppUserDao appUserDao;
    private final ConfirmationTokenService confirmationTokenService;

    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<List<AppUser>> getAllUsers() {

        return new ResponseEntity<>(appUserDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> registerUser(AppUser appUser) {

        //String hashedPassword = bCryptPasswordEncoder.encode(trainer.getPassword());
        //trainer.setPassword(hashedPassword);

        appUserDao.save(appUser);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserDao.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser (AppUser appUser){
        boolean userExists = appUserDao.
                findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists){
            throw new IllegalStateException("Email already taken");
        }
        /*String encodedPassword =
                bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);*/

        appUserDao.save(appUser);

        String token = UUID.randomUUID().toString();
        // TODO: Confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableAppUser(String email) {
        return appUserDao.enableAppUser(email);
    }
}