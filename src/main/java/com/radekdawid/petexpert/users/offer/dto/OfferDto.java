package com.radekdawid.petexpert.users.offer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    public OfferDto(String name, String description, Long price, boolean drivingToClient, Long drivingRadius, String city, Long serviceId, Long providerId, String providerName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.drivingToClient = drivingToClient;
        this.drivingRadius = drivingRadius;
        this.city = city;
        this.serviceId = serviceId;
        this.providerId = providerId;
        this.providerName = providerName;
    }
}
