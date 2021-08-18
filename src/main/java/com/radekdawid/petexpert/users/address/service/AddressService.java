package com.radekdawid.petexpert.users.address.service;

import com.radekdawid.petexpert.security.jwt.utils.JwtUtils;
import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.address.repository.AddressRepository;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public Address getAddress(Long id) {

        Optional<Address> address = addressRepository.getAddressById(id);

        if (address.isEmpty()) {
            throw new IllegalStateException("That address does not exists");
        }

        return address.get();
    }

    @Transactional
    public void update(Address address, HttpServletRequest request) {

        String token = jwtUtils.parseJwt(request);
        if (jwtUtils.validateJwtToken(token)) {
            Long userIdFromJwtToken = jwtUtils.getUserIdFromJwtToken(token);
            User userById = userService.getUserById(userIdFromJwtToken);
            if (userById.getAddress().getId().equals(address.getId()) || userById.getCompanies().stream().anyMatch(
                    company -> company.getAddresses().stream().anyMatch(address1 -> address1.getId().equals(address.getId())))) {
                addressRepository.save(address);
                return;
            }
        }
        throw new RuntimeException("Can not update address");
    }
}
