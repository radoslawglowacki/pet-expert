package com.radekdawid.petexpert.registration.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProviderRegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final Long roleId;
}
