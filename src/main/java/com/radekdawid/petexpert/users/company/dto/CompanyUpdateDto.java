package com.radekdawid.petexpert.users.company.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyUpdateDto {
    private Long id;
    private String name;
    private String description;
}
