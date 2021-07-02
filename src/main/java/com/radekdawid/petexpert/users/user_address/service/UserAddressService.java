package com.radekdawid.petexpert.users.user_address.service;

import com.radekdawid.petexpert.users.user_address.model.UserAddress;
import com.radekdawid.petexpert.users.user_address.repository.UserAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public UserAddress getAddress(Long id) {

        Optional<UserAddress> address = userAddressRepository.getAddressById(id);

        if (address.isEmpty()) {
            throw new IllegalStateException("That address does not exists");
        }

        return (UserAddress) address.get();
    }
}
