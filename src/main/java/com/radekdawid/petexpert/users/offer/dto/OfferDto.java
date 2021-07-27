package com.radekdawid.petexpert.users.offer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OfferDto {

    private Long id;
    private String name;
    private String description;
    private Long price;
    private boolean drivingToClient;
    private Long drivingRadius;
    private String city;
    private Long serviceId;
    private Long providerId;
    private String providerName;


}
