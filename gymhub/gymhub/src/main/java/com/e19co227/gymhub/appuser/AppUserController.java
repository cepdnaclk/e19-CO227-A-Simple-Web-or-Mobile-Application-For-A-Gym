package com.e19co227.gymhub.appuser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class AppUserController {


    AppUserService appUserService;
    @GetMapping("allUsers")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return null;
    }


    @PostMapping("register")
    public ResponseEntity<String> registerTrainer(@RequestBody AppUser appUser){
        return appUserService.registerUser(appUser);
    }
}
