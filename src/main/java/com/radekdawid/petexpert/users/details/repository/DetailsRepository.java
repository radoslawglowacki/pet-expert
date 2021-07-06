package com.radekdawid.petexpert.users.details.repository;

import com.radekdawid.petexpert.users.details.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
}
