package com.radekdawid.petexpert.registration.service;

import com.radekdawid.petexpert.email.EmailService;
import com.radekdawid.petexpert.registration.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.registration.token.ConfirmationTokenService;
import com.radekdawid.petexpert.users.company_address.CompanyAddress;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserAccessRepository;
import com.radekdawid.petexpert.users.user_address.model.UserAddress;
import com.radekdawid.petexpert.users.user_company.Company;
import com.radekdawid.petexpert.users.user_details.UserDetails;
import com.radekdawid.petexpert.users.user_role.service.RoleService;
import com.radekdawid.petexpert.users.user_services.UserServices;
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

    public String register(ProviderRegistrationRequest request) {
        User newUser = createNewUser(request);
        String token = confirmationTokenService.createToken(newUser);

        emailService.buildRegistrationEmail(request.getFirstName(), request.getEmail(), token);
        userAccessRepository.save(newUser);
        return token;
    }


    @NotNull
    private User createNewUser(@NotNull ProviderRegistrationRequest request) {
        registrationValidator.userExistingChecker(request.getEmail());
        registrationValidator.passwordChecker(request.getPassword());
        registrationValidator.providerRoleChecker(request.getRoleId());

        User newUser = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.addRole(roleService.getRole(request.getRoleId()));

        UserAddress userAddress = new UserAddress(request.getUserCity(), request.getUserStreet(), request.getUserNumber(),
                request.getUserLocal(), request.getUserZip(), newUser);
        newUser.addAddress(userAddress);

        Company company = new Company(request.getCompanyName());
        CompanyAddress companyAddress = new CompanyAddress(request.getCompanyCity(), request.getCompanyStreet(), request.getCompanyNumber(), request.getCompanyLocal(), request.getCompanyZip(), company);
        company.addAddress(companyAddress);
        newUser.addCompany(company);

        UserDetails userDetails = new UserDetails(request.getNip(), request.getPhone(), newUser);
        newUser.setDetails(userDetails);

        UserServices userServices = new UserServices(request.isGroomer(), request.isVet(), request.isPetsitter(), request.isBehaviorist(), newUser);
        newUser.setServices(userServices);

        return newUser;
    }


}






