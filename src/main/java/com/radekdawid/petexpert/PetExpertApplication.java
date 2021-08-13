package com.radekdawid.petexpert;

import com.radekdawid.petexpert.auth.payload.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.auth.service.RegistrationService;
import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.offer.model.Offer;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.services.service.ServicesService;
import com.radekdawid.petexpert.users.socials.model.Socials;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class PetExpertApplication {

    private final RegistrationService registrationService;
    private final ServicesService servicesService;

    public static void main(String[] args) {
        SpringApplication.run(PetExpertApplication.class, args);
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initializationOfDefaultData() {

        Services VET = servicesService.getService(1L);
        Services BEH = servicesService.getService(2L);
        Services GRO = servicesService.getService(3L);
        Services HOT = servicesService.getService(4L);

        ProviderRegistrationRequest providerAdmin = new ProviderRegistrationRequest("Admin", "Admin",
                "Admin1234#", "admin@petexpert.pl", 987654321L, 1234567890L, "Warszawa",
                "Wiejska", "3", "3", "00-333", "Company three",
                "Warszawa", "Wiejska", "3", "3", "00-333",
                List.of(1L, 2L, 3L, 4L), "Nothing");

        Socials socialsOne = new Socials("facebook.com", "ig.com", "twitter.com", "www.aaa.com");


        ProviderRegistrationRequest providerRadek = new ProviderRegistrationRequest("Radek", "Radek",
                "Admin1234#", "radek@petexpert.pl", 987654321L, 1234567890L, "Warszawa",
                "Wiejska", "1", "1", "00-111", "Company one",
                "Warszawa", "Wiejska", "1", "1", "00-111",
                List.of(1L, 2L, 3L, 4L), "Nothing");

        Socials socialsTwo = new Socials("facebook.com", "ig.com", "twitter.com", "www.aaa.com");

        Company companyOne = new Company("vet tani", "opis vet tani");
        companyOne.addAddress(new Address("Warszawa", "Wiejska", "1", "1", "00-001"));
        companyOne.addAddress(new Address("Warszawa", "Wiejska", "1", "2", "00-001"));

        companyOne.addOffer(new Offer("Name offer 1", "Description 1", 110L, true, 100L, "Warszawa", VET, companyOne));
        companyOne.addOffer(new Offer("Name offer 2", "Description 2", 120L, true, 100L, "Warszawa", BEH, companyOne));
        companyOne.addOffer(new Offer("Name offer 3", "Description 3", 130L, true, 100L, "Warszawa", GRO, companyOne));
        companyOne.addOffer(new Offer("Name offer 4", "Description 4", 140L, true, 100L, "Warszawa", HOT, companyOne));
        companyOne.addOffer(new Offer("Name offer 5", "Description 5", 150L, true, 100L, "Warszawa", VET, companyOne));
        companyOne.addOffer(new Offer("Name offer 6", "Description 6", 160L, true, 100L, "Warszawa", BEH, companyOne));
        companyOne.addOffer(new Offer("Name offer 7", "Description 7", 170L, true, 100L, "Warszawa", GRO, companyOne));
        companyOne.addOffer(new Offer("Name offer 8", "Description 8", 180L, true, 100L, "Warszawa", HOT, companyOne));


        Company companyTwo = new Company("vet drogi", "opis vet drogi");
        companyTwo.addAddress(new Address("Warszawa", "Wiejska", "2", "1", "00-001"));

        companyTwo.addOffer(new Offer("Name offer 21", "Description 21", 110L, true, 100L, "Warszawa", VET, companyOne));
        companyTwo.addOffer(new Offer("Name offer 22", "Description 22", 120L, true, 100L, "Warszawa", BEH, companyOne));
        companyTwo.addOffer(new Offer("Name offer 23", "Description 23", 130L, true, 100L, "Warszawa", GRO, companyOne));
        companyTwo.addOffer(new Offer("Name offer 24", "Description 24", 140L, true, 100L, "Warszawa", HOT, companyOne));
        companyTwo.addOffer(new Offer("Name offer 25", "Description 25", 150L, true, 100L, "Warszawa", VET, companyOne));
        companyTwo.addOffer(new Offer("Name offer 26", "Description 26", 160L, true, 100L, "Warszawa", BEH, companyOne));
        companyTwo.addOffer(new Offer("Name offer 27", "Description 27", 170L, true, 100L, "Warszawa", GRO, companyOne));
        companyTwo.addOffer(new Offer("Name offer 28", "Description 28", 180L, true, 100L, "Warszawa", HOT, companyOne));


        ProviderRegistrationRequest providerDawid = new ProviderRegistrationRequest("Dawid", "Dawid",
                "Admin1234#", "dawid@petexpert.pl", 987654321L, 1234567890L, "Warszawa",
                "Wiejska", "2", "2", "00-222", "Company two",
                "Warszawa", "Wiejska", "2", "2", "00-222",
                List.of(1L, 2L, 3L, 4L), "Nothing");

        Socials socialsThree = new Socials("facebook.com", "ig.com", "twitter.com", "www.aaa.com");

        Company companyThree = new Company("beh cat", "opis beh cat");
        companyThree.addAddress(new Address("Warszawa", "Wiejska", "3", "1", "00-001"));

        companyThree.addOffer(new Offer("Name offer 31", "Description 31", 110L, true, 100L, "Warszawa", VET, companyOne));
        companyThree.addOffer(new Offer("Name offer 32", "Description 32", 120L, true, 100L, "Warszawa", BEH, companyOne));
        companyThree.addOffer(new Offer("Name offer 33", "Description 33", 130L, true, 100L, "Warszawa", GRO, companyOne));
        companyThree.addOffer(new Offer("Name offer 34", "Description 34", 140L, true, 100L, "Warszawa", HOT, companyOne));
        companyThree.addOffer(new Offer("Name offer 35", "Description 35", 150L, true, 100L, "Warszawa", VET, companyOne));
        companyThree.addOffer(new Offer("Name offer 36", "Description 36", 160L, true, 100L, "Warszawa", BEH, companyOne));
        companyThree.addOffer(new Offer("Name offer 37", "Description 37", 170L, true, 100L, "Warszawa", GRO, companyOne));
        companyThree.addOffer(new Offer("Name offer 38", "Description 38", 180L, true, 100L, "Warszawa", HOT, companyOne));


        registrationService.registerDefaultProvider(providerAdmin, null, socialsOne);
        registrationService.registerDefaultProvider(providerRadek, List.of(companyOne, companyTwo), socialsTwo);
        registrationService.registerDefaultProvider(providerDawid, List.of(companyThree), socialsThree);
    }
}
