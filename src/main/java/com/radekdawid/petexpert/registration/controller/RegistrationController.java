package com.radekdawid.petexpert.registration.controller;

import com.radekdawid.petexpert.registration.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.registration.request.UserRegistrationRequest;
import com.radekdawid.petexpert.registration.service.ProviderRegistrationService;
import com.radekdawid.petexpert.registration.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final ProviderRegistrationService providerRegistrationService;

    @PostMapping("/user")
    public String register(@RequestBody UserRegistrationRequest request){
        return userRegistrationService.register(request);
    }

    @PostMapping("/provider")
    public String register(@RequestBody ProviderRegistrationRequest request){
        return providerRegistrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token){
        return userRegistrationService.confirmToken(token);
    }
}
