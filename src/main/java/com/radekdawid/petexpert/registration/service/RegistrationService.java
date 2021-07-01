package com.radekdawid.petexpert.registration.service;

import com.radekdawid.petexpert.email.EmailSender;
import com.radekdawid.petexpert.registration.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.registration.request.UserRegistrationRequest;
import com.radekdawid.petexpert.registration.token.ConfirmationToken;
import com.radekdawid.petexpert.registration.token.ConfirmationTokenService;
import com.radekdawid.petexpert.users.role.service.RoleService;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserAccessRepository;
import com.radekdawid.petexpert.users.user.service.UserService;
import com.radekdawid.petexpert.validation.registration.UserRegistrationValidator;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final UserAccessRepository userAccessRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final UserService userService;
    private final UserRegistrationValidator userRegistrationValidator;

    public String registerUser(@NotNull UserRegistrationRequest request) {

//        TODO: if email not confirmed send confirmation email
//        TODO: check of attributes are the same

        userRegistrationValidator.userExistingChecker(request);
        userRegistrationValidator.passwordChecker(request);
        userRegistrationValidator.userRoleChecker(request);

        User newUser = createNewUser(request);
        String token = createToken(newUser);
        buildRegistrationEmail(request, token);
        return token;
    }


    public String registerProvider(ProviderRegistrationRequest request) {

        return null;
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }


    private User createNewUser(UserRegistrationRequest request) {
        User newUser = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.addRole(roleService.getRole(request.getRoleId()));
        userAccessRepository.save(newUser);
        return newUser;
    }

    private String createToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    @SneakyThrows
    @Contract(pure = true)
    private void buildRegistrationEmail(UserRegistrationRequest request, String token) {
        ClassPathResource resource = new ClassPathResource("templates/registrationEmail.txt");
        InputStream inputStream = resource.getInputStream();
        String template = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining());


//        TODO: Change link to proper one in future
        String confirmationLink = "http://localhost:8081/api/v1/registration/confirm?token=" + token;
        String subject = "Pet Expert - confirm your email";
        String email = String.format(template, request.getFirstName(), confirmationLink);
        emailSender.send(request.getEmail(), email, subject);
    }


}
