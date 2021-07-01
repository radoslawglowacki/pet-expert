package com.radekdawid.petexpert.users.user_address.repository;

import com.radekdawid.petexpert.users.user_address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
Optional<Address> getAddressById(Long id);
}
