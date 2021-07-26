package com.radekdawid.petexpert.users.offer.service;

import com.radekdawid.petexpert.users.offer.model.Offer;
import com.radekdawid.petexpert.users.offer.model.OfferPage;
import com.radekdawid.petexpert.users.offer.repository.OfferRepository;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityTransaction;

@AllArgsConstructor
@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    public Page<Offer> getOffers(OfferPage offerPage) {
        Sort sort = Sort.by(offerPage.getSortDirection(), offerPage.getSortBy());
        Pageable pageable = PageRequest.of(offerPage.getPageNumber(), offerPage.getPageSize(), sort);

        return offerRepository.findAll(pageable);
    }

    @Transactional
    public Offer addOffer(Offer offer, Long id) {
        User byId = userRepository.getById(id);
        byId.addOffer(offer);
        return offer;
    }

}
