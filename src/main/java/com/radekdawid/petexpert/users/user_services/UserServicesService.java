package com.radekdawid.petexpert.users.user_services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServicesService {
    private final UserServicesRepository userServicesRepository;
}
