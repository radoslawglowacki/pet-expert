package com.radekdawid.petexpert.validation.registration;

import com.radekdawid.petexpert.users.user.repository.UserRepository;
import com.radekdawid.petexpert.users.role.model.Role;
import com.radekdawid.petexpert.users.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static final String userRole = "USER";
    private static final String providerRole = "PROVIDER";
    public final UserRepository userRepository;
    public final RoleService roleService;

    public RegistrationValidator(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public void passwordChecker(String password) {
        Matcher matcherPassword = pattern.matcher(password);

        if (!matcherPassword.matches()) {
            throw new IllegalStateException("Incorrect password");
        }
    }

    public void userExistingChecker(String email) {
        boolean userExists = userRepository.findByEmail(email).isPresent();

        if (userExists) {
            throw new IllegalStateException("Email is already taken");
        }
    }

    public void userRoleChecker(Long roleId) {
        Role role = roleService.getRole(roleId);

        if (!role.getName().equals(RegistrationValidator.userRole)) {
            throw new IllegalStateException("Incorrect user role");
        }
    }

    public void providerRoleChecker(Long roleId) {
        Role role = roleService.getRole(roleId);

        if (!role.getName().equals(RegistrationValidator.providerRole)) {
            throw new IllegalStateException("Incorrect user role");
        }
    }
}
