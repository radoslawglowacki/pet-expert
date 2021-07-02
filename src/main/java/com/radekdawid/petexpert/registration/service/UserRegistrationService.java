package com.radekdawid.petexpert.registration.service;

import com.radekdawid.petexpert.email.EmailService;
import com.radekdawid.petexpert.registration.request.UserRegistrationRequest;
import com.radekdawid.petexpert.registration.token.ConfirmationTokenService;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserAccessRepository;
import com.radekdawid.petexpert.users.user_role.service.RoleService;
import com.radekdawid.petexpert.validation.registration.RegistrationValidator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserRegistrationService {

    private final ConfirmationTokenService confirmationTokenService;
    private final UserAccessRepository userAccessRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final RegistrationValidator registrationValidator;
    private final EmailService emailService;

    //        TODO: if email not confirmed send confirmation email
//        TODO: check of attributes are the same
    public String register(@NotNull UserRegistrationRequest request) {
        User newUser = createNewUser(request);
        String token = confirmationTokenService.createToken(newUser);

        userAccessRepository.save(newUser);
        emailService.buildRegistrationEmail(request.getFirstName(), request.getEmail(), token);

        return token;
    }


    @NotNull
    private User createNewUser(UserRegistrationRequest request) {
        registrationValidator.userExistingChecker(request.getEmail());
        registrationValidator.passwordChecker(request.getPassword());
        registrationValidator.userRoleChecker(request.getRoleId());

        User newUser = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.addRole(roleService.getRole(request.getRoleId()));
        return newUser;
    }
}
