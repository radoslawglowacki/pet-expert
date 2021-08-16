package com.radekdawid.petexpert.users.offer.service;

import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.company.service.CompanyService;
import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import com.radekdawid.petexpert.users.offer.mapper.OfferMapper;
import com.radekdawid.petexpert.users.offer.model.Offer;
import com.radekdawid.petexpert.users.offer.model.OfferPage;
import com.radekdawid.petexpert.users.offer.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final CompanyService companyService;

    public Page<OfferDto> getOffers(OfferPage offerPage) {
        Sort sort = Sort.by(offerPage.getSortDirection(), offerPage.getSortBy());
        Pageable pageable = PageRequest.of(offerPage.getPageNumber(), offerPage.getPageSize(), sort);

        Page<Offer> all = offerRepository.findAll(pageable);
        ArrayList<OfferDto> offerDtos = new ArrayList<>();

        for (Offer element : all) {
            offerDtos.add(offerMapper.map(element));
        }
        return new PageImpl<>(offerDtos);
    }


    @Transactional
    public OfferDto addOffer(OfferDto offerDto) {
        Company company = companyService.getCompany(offerDto.getProviderId());
        company.addOffer(offerMapper.map(offerDto));
        return offerDto;
    }
}
