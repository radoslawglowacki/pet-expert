package com.radekdawid.petexpert.validation.registration;

import com.radekdawid.petexpert.registration.request.UserRegistrationRequest;
import com.radekdawid.petexpert.users.role.model.Role;
import com.radekdawid.petexpert.users.role.service.RoleService;
import com.radekdawid.petexpert.users.user.repository.UserAccessRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserRegistrationValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static final String userRole = "USER";
    public final UserAccessRepository userAccessRepository;
    public final RoleService roleService;

    public UserRegistrationValidator(UserAccessRepository userAccessRepository, RoleService roleService) {
        this.userAccessRepository = userAccessRepository;
        this.roleService = roleService;
    }

    public void passwordChecker(UserRegistrationRequest request){
        Matcher matcherPassword = pattern.matcher(request.getPassword());

        if (!matcherPassword.matches()) {
            throw new IllegalStateException("Incorrect password");
        }
    }

    public void userExistingChecker(UserRegistrationRequest request){
        boolean userExists = userAccessRepository.findByEmail(request.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("Email is already taken");
        }
    }

    public void userRoleChecker(UserRegistrationRequest request){
        Role role = roleService.getRole(request.getRoleId());

        if(!role.getName().equals(userRole)){
            throw new IllegalStateException("Incorrect user role");
        }
    }
}
