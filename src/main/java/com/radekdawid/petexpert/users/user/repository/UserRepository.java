package com.radekdawid.petexpert.users.user.repository;

import com.radekdawid.petexpert.users.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
