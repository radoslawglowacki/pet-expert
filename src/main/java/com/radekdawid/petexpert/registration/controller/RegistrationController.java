package com.radekdawid.petexpert.registration.controller;

import com.radekdawid.petexpert.registration.payload.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.registration.payload.request.UserRegistrationRequest;
import com.radekdawid.petexpert.registration.service.ProviderRegistrationService;
import com.radekdawid.petexpert.registration.service.UserRegistrationService;
import com.radekdawid.petexpert.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final ProviderRegistrationService providerRegistrationService;
    private final ConfirmationTokenService confirmationTokenService;
    private final Environment env;

    @PostMapping("/user")
    public void register(@RequestBody UserRegistrationRequest request){
        userRegistrationService.register(request);
    }

    @PostMapping("/provider")
    public void register(@RequestBody ProviderRegistrationRequest request){
        providerRegistrationService.register(request);
    }

    @SneakyThrows
    @GetMapping(path = "/confirm")
    public void confirm(@RequestParam("token") String token, HttpServletResponse response){
        confirmationTokenService.confirmToken(token);
        response.sendRedirect(env.getProperty("PET_EXPERT_MAIN_PAGE"));
    }
}
