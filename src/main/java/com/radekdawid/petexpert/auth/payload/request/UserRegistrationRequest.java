package com.radekdawid.petexpert.auth.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
}
