package com.radekdawid.petexpert.users.user.repository;

import com.radekdawid.petexpert.users.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
