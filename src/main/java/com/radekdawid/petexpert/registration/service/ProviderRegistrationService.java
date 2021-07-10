package com.radekdawid.petexpert.registration.service;

import com.radekdawid.petexpert.email.EmailService;
import com.radekdawid.petexpert.registration.payload.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.registration.token.ConfirmationTokenService;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserAccessRepository;
import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.details.model.Details;
import com.radekdawid.petexpert.users.role.service.RoleService;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.validation.registration.RegistrationValidator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProviderRegistrationService {


    private final RegistrationValidator registrationValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserAccessRepository userAccessRepository;
    private final EmailService emailService;
    private final Long providerRoleNumber = 2L;

    public void register(ProviderRegistrationRequest request) {
        User newUser = createNewUser(request);
        String token = confirmationTokenService.createToken(newUser);

        emailService.buildRegistrationEmail(request.getFirstName(), request.getEmail(), token);
        userAccessRepository.save(newUser);
    }


    @NotNull
    private User createNewUser(@NotNull ProviderRegistrationRequest request) {
        registrationValidator.userExistingChecker(request.getEmail());
        registrationValidator.passwordChecker(request.getPassword());

        User newUser = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.addRole(roleService.getRole(providerRoleNumber));
        newUser.addAddress(new Address(request.getUserCity(), request.getUserStreet(), request.getUserNumber(),
                request.getUserLocal(), request.getUserZip()));
        newUser.addCompany(createUserCompany(request));
        newUser.setDetails(new Details(request.getNip(), request.getPhone(), newUser));
        newUser.setServices(new Services(request.isGroomer(), request.isVet(), request.isPetsitter(), request.isBehaviorist(), newUser));

        return newUser;
    }

    private Company createUserCompany(@NotNull ProviderRegistrationRequest request){
        Company company = new Company(request.getCompanyName());
        Address companyAddress = new Address(request.getCompanyCity(), request.getCompanyStreet(), request.getCompanyNumber(), request.getCompanyLocal(), request.getCompanyZip());
        company.addAddress(companyAddress);
        return company;
    }

}






