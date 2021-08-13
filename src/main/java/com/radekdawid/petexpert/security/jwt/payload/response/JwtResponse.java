package com.radekdawid.petexpert.security.jwt.payload.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;


    public JwtResponse(String accessToken, String refreshToken, Long id, @Email(message = "Email is not valid") String userDetailsEmail, List<String> roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
    }
}
