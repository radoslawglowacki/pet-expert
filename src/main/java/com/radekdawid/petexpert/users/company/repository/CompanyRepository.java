package com.radekdawid.petexpert.users.company.repository;

import com.radekdawid.petexpert.users.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> getCompanyById(Long id);
}
