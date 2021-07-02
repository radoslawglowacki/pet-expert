package com.radekdawid.petexpert.users.company_address;

import com.radekdawid.petexpert.users.user_address.model.UserAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyAddressService {

    private final CompanyAddressRepository companyAddressRepository;

    public CompanyAddress getAddress(Long id) {

        Optional<CompanyAddress> address = companyAddressRepository.getCompanyAddressById(id);

        if (address.isEmpty()) {
            throw new IllegalStateException("That address does not exists");
        }

        return address.get();
    }
}
