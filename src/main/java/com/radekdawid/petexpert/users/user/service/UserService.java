package com.radekdawid.petexpert.users.user.service;


import com.radekdawid.petexpert.security.PasswordEncoder;
import com.radekdawid.petexpert.users.role.service.RoleService;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserAccessRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email: %s not found";
    private final UserAccessRepository userAccessRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

//    @PostConstruct
//    public void init() {
//
//        User user = new User("Radek", "Radek", "radek@petexpert.pl", passwordEncoder.bCryptPasswordEncoder().encode("Radek1234#"));
//        user.setEnabled(true);
//        user.addRole(roleService.getRole(1L));
//        userAccessRepository.save(user);
//
//        User admin = new User("Admin", "Admin", "admin@petexpert.pl", passwordEncoder.bCryptPasswordEncoder().encode("Admin1234#"));
//        admin.setEnabled(true);
//        admin.addRole(roleService.getRole(1L));
//        admin.addRole(roleService.getRole(2L));
//        admin.addRole(roleService.getRole(3L));
//        userAccessRepository.save(admin);
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAccessRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

    }


    public void enableAppUser(String email) {
        userAccessRepository.enableUser(email);
    }
}
