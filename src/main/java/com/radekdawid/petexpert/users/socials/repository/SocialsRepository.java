package com.radekdawid.petexpert.users.socials.repository;

import com.radekdawid.petexpert.users.socials.model.Socials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialsRepository extends JpaRepository<Socials, Long> {
    Optional<Socials> getUserSocialsById(Long id);
}
