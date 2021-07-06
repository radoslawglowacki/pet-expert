package com.radekdawid.petexpert.users.socials.service;

import com.radekdawid.petexpert.users.socials.model.Socials;
import com.radekdawid.petexpert.users.socials.repository.SocialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class SocialsService {

    private final SocialsRepository socialsRepository;

    public Socials getSocials(Long id) {
        Optional<Socials> socials = socialsRepository.getUserSocialsById(id);
        if (socials.isEmpty()) {
            throw new IllegalStateException("User socials does not exists");
        }

        return socials.get();
    }
}