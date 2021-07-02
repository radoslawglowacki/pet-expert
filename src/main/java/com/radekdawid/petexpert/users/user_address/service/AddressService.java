package com.radekdawid.petexpert.users.user_address.service;

import com.radekdawid.petexpert.users.user_address.model.UserAddress;
import com.radekdawid.petexpert.users.user_address.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public UserAddress getAddress(Long id) {

        Optional<UserAddress> address = addressRepository.getAddressById(id);

        if (address.isEmpty()) {
            throw new IllegalStateException("That address does not exists");
        }

        return (UserAddress) address.get();
    }
}
