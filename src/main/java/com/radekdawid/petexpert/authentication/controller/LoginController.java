package com.radekdawid.petexpert.authentication.controller;

import com.radekdawid.petexpert.authentication.payload.request.LoginRequest;
import com.radekdawid.petexpert.authentication.payload.request.TokenRefreshRequest;
import com.radekdawid.petexpert.authentication.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request){
        return loginService.refreshToken(request);
    }

}
