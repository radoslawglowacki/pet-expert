package com.radekdawid.petexpert.users.user_address.repository;

import com.radekdawid.petexpert.users.user_address.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long> {
Optional<UserAddress> getAddressById(Long id);
}
