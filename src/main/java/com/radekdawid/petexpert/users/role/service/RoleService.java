package com.radekdawid.petexpert.users.role.service;

import com.radekdawid.petexpert.users.role.model.Role;
import com.radekdawid.petexpert.users.role.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(Long id) {

        Optional role = roleRepository.getRoleById(id);

        if (role.isEmpty()) {
            throw new IllegalStateException("That role does not exists");
        }

        return (Role) role.get();
    }

}
