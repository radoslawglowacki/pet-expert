package com.radekdawid.petexpert.users.user.dto;

import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.company.dto.CompanyDto;
import com.radekdawid.petexpert.users.details.dto.DetailsDto;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.socials.dto.SocialsDto;
import com.radekdawid.petexpert.users.socials.model.Socials;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserPageDto {
    private String firstName;
    private String lastName;
    private String email;
    private DetailsDto details;
    private Address address;
    private SocialsDto socials;

    private List<Services> services;
    private List<CompanyDto> companies;


}
