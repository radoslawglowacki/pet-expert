package com.radekdawid.petexpert.users.socials.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SocialsDto {
    private Long id;
    private String facebook;
    private String instagram;
    private String twitter;
    private String website;
}
