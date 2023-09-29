package com.e19co227.gymhub.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    @PostMapping("user")
    public String registerUser(@RequestBody RegistrationRequest request){

        return registrationService.registerUser(request);
    }

//    @PostMapping("trainer")
//    public String registerTrainer(@RequestBody RegistrationRequest request){
//
//        return registrationService.registerTrainer(request);
//    }
    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token){

        return registrationService.confirmToken(token);
    }
}
