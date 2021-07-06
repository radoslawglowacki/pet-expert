package com.radekdawid.petexpert.users.services.repository;

import com.radekdawid.petexpert.users.services.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
}
