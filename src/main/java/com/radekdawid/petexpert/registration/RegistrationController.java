package com.radekdawid.petexpert.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/user")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.registerUser(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
