package com.radekdawid.petexpert.users.services.service;


import com.radekdawid.petexpert.users.services.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesService {
    private final ServicesRepository servicesRepository;
}
