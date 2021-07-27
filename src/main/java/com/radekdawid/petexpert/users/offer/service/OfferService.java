package com.radekdawid.petexpert.users.offer.service;

import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import com.radekdawid.petexpert.users.offer.mapper.OfferMapper;
import com.radekdawid.petexpert.users.offer.model.Offer;
import com.radekdawid.petexpert.users.offer.model.OfferPage;
import com.radekdawid.petexpert.users.offer.repository.OfferRepository;
import com.radekdawid.petexpert.users.services.repository.ServicesRepository;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final OfferMapper offerMapper;
    private final ServicesRepository servicesRepository;

    public Page<OfferDto> getOffers(OfferPage offerPage) {
        Sort sort = Sort.by(offerPage.getSortDirection(), offerPage.getSortBy());
        Pageable pageable = PageRequest.of(offerPage.getPageNumber(), offerPage.getPageSize(), sort);

        Page<Offer> all = offerRepository.findAll(pageable);
        ArrayList<OfferDto> offerDtos = new ArrayList<>();

        for (Offer element: all) {
            offerDtos.add(offerMapper.map(element));
        }

        return new PageImpl<>(offerDtos);
    }


    @Transactional
    public OfferDto addOffer(OfferDto offerDto) {
        User byId = userRepository.getById(offerDto.getProviderId());
        byId.addOffer(offerMapper.map(offerDto));
        return offerDto;
    }
}
