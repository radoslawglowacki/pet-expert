package com.radekdawid.petexpert.users.offer.mapper;


import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.company.service.CompanyService;
import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import com.radekdawid.petexpert.users.offer.model.Offer;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.services.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OfferMapper {

    private final ServicesService servicesService;
    private final CompanyService companyService;

    public OfferDto map(Offer offer) {
        Company company = offer.getCompany();

        return new OfferDto(offer.getId(), offer.getName(), offer.getDescription(), offer.getPrice(), offer.isDrivingToClient(),
                offer.getDrivingRadius(), offer.getCity(), offer.getService().getId(), company.getId(), company.getName());
    }

    public Offer map(OfferDto offerDto) {
        Services service = servicesService.getService(offerDto.getServiceId());

        Company company = companyService.getCompanyById(offerDto.getProviderId());

        return new Offer(offerDto.getName(), offerDto.getDescription(), offerDto.getPrice(),
                offerDto.isDrivingToClient(), offerDto.getDrivingRadius(), offerDto.getCity(), service, company);
    }
}
