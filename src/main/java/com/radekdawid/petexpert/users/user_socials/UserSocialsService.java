package com.radekdawid.petexpert.users.user_socials;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserSocialsService {

    private final UserSocialsRepository userSocialsRepository;

    public UserSocials getSocials(Long id) {
        Optional<UserSocials> socials = userSocialsRepository.getUserSocialsById(id);
        if (socials.isEmpty()) {
            throw new IllegalStateException("User socials does not exists");
        }

        return socials.get();
    }
}