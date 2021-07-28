package com.radekdawid.petexpert.users.offer.mapper;


import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import com.radekdawid.petexpert.users.offer.model.Offer;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.services.service.ServicesService;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

    private final ServicesService servicesService;
    private final UserService userService;

    public OfferMapper(ServicesService servicesService, UserService userService) {
        this.servicesService = servicesService;
        this.userService = userService;
    }

    public OfferDto map(Offer offer){
        User user = offer.getUser();

        return new OfferDto(offer.getId(), offer.getName(), offer.getDescription(), offer.getPrice(), offer.isDrivingToClient(),
               offer.getDrivingRadius(), offer.getCity(), offer.getService().getId(), user.getId(), user.getFirstName());
    }

    public Offer map(OfferDto offerDto){
        Services service = servicesService.getService(offerDto.getServiceId());
        User userById = userService.getUserById(offerDto.getProviderId());
        return new Offer(offerDto.getName(), offerDto.getDescription(), offerDto.getPrice(),
                offerDto.isDrivingToClient(), offerDto.getDrivingRadius(), offerDto.getCity(), service, userById);
    }
}
