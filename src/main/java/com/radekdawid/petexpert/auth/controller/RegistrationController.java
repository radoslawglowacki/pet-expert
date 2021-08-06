package com.radekdawid.petexpert.auth.controller;

import com.radekdawid.petexpert.auth.payload.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.auth.payload.request.UserRegistrationRequest;
import com.radekdawid.petexpert.auth.service.RegistrationService;
import com.radekdawid.petexpert.auth.tokens.confirmationToken.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;
    private final Environment env;

    @PostMapping("/user")
    public void register(@RequestBody UserRegistrationRequest request) {
        registrationService.registerUser(request);
    }

    @PostMapping("/provider")
    public void register(@RequestBody ProviderRegistrationRequest request) {
        registrationService.registerProvider(request);
    }

    @SneakyThrows
    @GetMapping(path = "/confirm")
    public void confirm(@RequestParam("token") String token, HttpServletResponse response) {
        confirmationTokenService.confirmToken(token);
        response.sendRedirect(env.getProperty("PET_EXPERT_MAIN_PAGE"));
    }
}
