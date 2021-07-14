package com.radekdawid.petexpert.authentication.payload.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, @Email(message = "Email is not valid") String userDetailsEmail, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = userDetailsEmail;
        this.roles = roles;
    }
}
