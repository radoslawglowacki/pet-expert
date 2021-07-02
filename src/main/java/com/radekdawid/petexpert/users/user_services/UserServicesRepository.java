package com.radekdawid.petexpert.users.user_services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServicesRepository extends JpaRepository<UserServices, Long> {
}
