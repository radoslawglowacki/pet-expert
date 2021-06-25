package com.radekdawid.petexpert.user.repository;

import com.radekdawid.petexpert.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
