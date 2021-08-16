package com.radekdawid.petexpert.users.offer.controller;


import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import com.radekdawid.petexpert.users.offer.model.OfferPage;
import com.radekdawid.petexpert.users.offer.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<Page<OfferDto>> getOffers(OfferPage offerPage) {
        return new ResponseEntity<>(offerService.getOffers(offerPage), HttpStatus.OK);
    }


    @PostMapping("/new")
//    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<OfferDto> addOffer(@RequestBody OfferDto offerDto) {
        return new ResponseEntity<>(offerService.addOffer(offerDto), HttpStatus.OK);
    }

}
