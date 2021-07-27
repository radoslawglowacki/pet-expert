package com.radekdawid.petexpert.users.services.service;


import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.services.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicesService {
    private final ServicesRepository servicesRepository;


    public Services getService(Long id) {

        Optional service = servicesRepository.getServicesById(id);

        if (service.isEmpty()) {
            throw new IllegalStateException("That service does not exists");
        }

        return (Services) service.get();
    }

    public Services getServiceByName(String name){
         return servicesRepository.getByName(name).orElseThrow();
    }

}
