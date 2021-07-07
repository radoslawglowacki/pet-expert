package com.radekdawid.petexpert.registration.controller;

import com.radekdawid.petexpert.registration.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.registration.request.UserRegistrationRequest;
import com.radekdawid.petexpert.registration.service.ProviderRegistrationService;
import com.radekdawid.petexpert.registration.service.UserRegistrationService;
import com.radekdawid.petexpert.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final ProviderRegistrationService providerRegistrationService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("/user")
    public String register(@RequestBody UserRegistrationRequest request){
        return userRegistrationService.register(request);
    }

    @PostMapping("/provider")
    public String register(@RequestBody ProviderRegistrationRequest request){
        return providerRegistrationService.register(request);
    }

    @SneakyThrows
    @GetMapping(path = "/confirm")
    public void confirm(@RequestParam("token") String token, HttpServletResponse response){
        confirmationTokenService.confirmToken(token);
//        TODO: app properties
        response.sendRedirect("http://localhost:3000");
    }
}
