package com.radekdawid.petexpert.users.address.service;

import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.address.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address getAddress(Long id) {

        Optional<Address> address = addressRepository.getAddressById(id);

        if (address.isEmpty()) {
            throw new IllegalStateException("That address does not exists");
        }

        return (Address) address.get();
    }

    @Transactional
    public void update(Address address) {
        addressRepository.save(address);
    }
}
