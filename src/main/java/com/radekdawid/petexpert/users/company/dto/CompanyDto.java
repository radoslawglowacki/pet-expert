package com.radekdawid.petexpert.users.company.dto;

import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    private List<Address> addressList;
    private List<OfferDto> offerList;
}
