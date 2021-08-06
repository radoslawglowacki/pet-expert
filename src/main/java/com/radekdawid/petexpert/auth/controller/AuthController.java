package com.radekdawid.petexpert.auth.controller;

import com.radekdawid.petexpert.auth.payload.request.LoginRequest;
import com.radekdawid.petexpert.security.jwt.payload.request.TokenRefreshRequest;
import com.radekdawid.petexpert.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/signin", consumes = "application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request){
        return authService.refreshToken(request);
    }

}
