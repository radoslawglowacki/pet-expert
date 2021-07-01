package com.radekdawid.petexpert.registration.controller;

import com.radekdawid.petexpert.registration.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.registration.request.UserRegistrationRequest;
import com.radekdawid.petexpert.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/user")
    public String register(@RequestBody UserRegistrationRequest request){
        return registrationService.registerUser(request);
    }

    @PostMapping("/provider")
    public String register(@RequestBody ProviderRegistrationRequest request){
        return registrationService.registerProvider(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
