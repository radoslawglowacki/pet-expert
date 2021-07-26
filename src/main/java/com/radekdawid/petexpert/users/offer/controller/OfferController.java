package com.radekdawid.petexpert.users.offer.controller;


import com.radekdawid.petexpert.users.offer.model.Offer;
import com.radekdawid.petexpert.users.offer.model.OfferPage;
import com.radekdawid.petexpert.users.offer.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<Page<Offer>> getOffers(OfferPage offerPage){
        return new ResponseEntity<>(offerService.getOffers(offerPage), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer, Long id){
        return new ResponseEntity<>(offerService.addOffer(offer, id), HttpStatus.OK);
    }

}
