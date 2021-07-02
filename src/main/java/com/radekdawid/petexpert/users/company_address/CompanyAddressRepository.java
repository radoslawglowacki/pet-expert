package com.radekdawid.petexpert.users.company_address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyAddressRepository  extends JpaRepository<CompanyAddress, Long> {
    Optional<CompanyAddress> getCompanyAddressById(Long id);
}
