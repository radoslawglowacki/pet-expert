package com.radekdawid.petexpert.users.user.service;


import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.user.dto.UserPageDto;
import com.radekdawid.petexpert.users.user.mapper.UserPageMapper;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email: %s not found";
    private final UserRepository userRepository;
    private final UserPageMapper userPageMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public List<Company> getUserCompanies(Long userId) {
        User byId = userRepository.getById(userId);
        return new ArrayList<>(byId.getCompanies());
    }

    public void enableAppUser(String email) {
        userRepository.enableUser(email);
    }

    public UserPageDto getUserDetailsPage(String token) {
        return userPageMapper.map(token);
    }
}
