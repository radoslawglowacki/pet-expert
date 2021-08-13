package com.radekdawid.petexpert.users.details.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailsDto {
    private Long id;
    private Long nip;
    private Long phone;
    private String description;
}
