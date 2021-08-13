package com.radekdawid.petexpert.users.company.mapper;


import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.company.dto.CompanyDto;
import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import com.radekdawid.petexpert.users.offer.mapper.OfferMapper;
import com.radekdawid.petexpert.users.offer.model.Offer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Component
@AllArgsConstructor
public class CompanyMapper {

    private final OfferMapper offerMapper;

    public CompanyDto map(Company company){
        List<OfferDto> offerDtoList = new ArrayList<>();
        ArrayList<Address> addresses = new ArrayList<>(company.getAddresses());

        for (Offer offer:company.getOffers()) {
        offerDtoList.add(offerMapper.map(offer));
        }

        addresses.sort(Comparator.comparing(Address::getId));
        offerDtoList.sort(Comparator.comparing(OfferDto::getName));

        return new CompanyDto(company.getId(), company.getName(), company.getDescription(), addresses,offerDtoList);

    }
}
