package com.radekdawid.petexpert.validation.registration;

import com.radekdawid.petexpert.registration.request.UserRegistrationRequest;
import com.radekdawid.petexpert.users.user_role.model.Role;
import com.radekdawid.petexpert.users.user_role.service.RoleService;
import com.radekdawid.petexpert.users.user.repository.UserAccessRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static final String userRole = "USER";
    public final UserAccessRepository userAccessRepository;
    public final RoleService roleService;

    public RegistrationValidator(UserAccessRepository userAccessRepository, RoleService roleService) {
        this.userAccessRepository = userAccessRepository;
        this.roleService = roleService;
    }

    public void passwordChecker(String password){
        Matcher matcherPassword = pattern.matcher(password);

        if (!matcherPassword.matches()) {
            throw new IllegalStateException("Incorrect password");
        }
    }

    public void userExistingChecker(String email){
        boolean userExists = userAccessRepository.findByEmail(email).isPresent();

        if (userExists) {
            throw new IllegalStateException("Email is already taken");
        }
    }

    public void userRoleChecker(Long roleId){
        Role role = roleService.getRole(roleId);

        if(!role.getName().equals(RegistrationValidator.userRole)){
            throw new IllegalStateException("Incorrect user role");
        }
    }
}
