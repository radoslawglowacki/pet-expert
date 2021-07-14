package com.radekdawid.petexpert.authentication.controller;

import com.radekdawid.petexpert.authentication.payload.request.LoginRequest;
import com.radekdawid.petexpert.authentication.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(path = "/signin", consumes = "application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.loginUser(loginRequest);
    }

}