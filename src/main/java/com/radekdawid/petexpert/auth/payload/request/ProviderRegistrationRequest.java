package com.radekdawid.petexpert.auth.payload.request;


import com.radekdawid.petexpert.users.address.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProviderRegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;

    private final Long phone;
    private final Long nip;

    private final String userCity;
    private final String userStreet;
    private final String userNumber;
    private final String userLocal;
    private final String userZip;

    private final String companyName;
    private final String companyCity;
    private final String companyStreet;
    private final String companyNumber;
    private final String companyLocal;
    private final String companyZip;

    private final List<Long> services;

    private final String adminMessage;


//    public Address getAddres(){
//        return new Address()
//    }

}
