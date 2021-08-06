package com.radekdawid.petexpert.auth.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
