package com.radekdawid.petexpert.users.details.service;

import com.radekdawid.petexpert.users.details.repository.DetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DetailsService {
    private final DetailsRepository detailsRepository;
}
