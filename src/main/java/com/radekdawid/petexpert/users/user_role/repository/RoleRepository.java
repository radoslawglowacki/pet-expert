package com.radekdawid.petexpert.users.user_role.repository;

import com.radekdawid.petexpert.users.user_role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleById(Long id);
}
