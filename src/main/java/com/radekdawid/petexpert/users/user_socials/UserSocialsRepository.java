package com.radekdawid.petexpert.users.user_socials;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSocialsRepository extends JpaRepository<UserSocials, Long> {
    Optional<UserSocials> getUserSocialsById(Long id);
}
