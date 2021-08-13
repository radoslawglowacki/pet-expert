package com.radekdawid.petexpert.auth.service;

import com.radekdawid.petexpert.auth.payload.request.UserRegistrationRequest;
import com.radekdawid.petexpert.email.EmailService;
import com.radekdawid.petexpert.auth.payload.request.ProviderRegistrationRequest;
import com.radekdawid.petexpert.auth.tokens.confirmationToken.ConfirmationTokenService;
import com.radekdawid.petexpert.users.services.service.ServicesService;
import com.radekdawid.petexpert.users.socials.model.Socials;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.repository.UserRepository;
import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.details.model.Details;
import com.radekdawid.petexpert.users.role.service.RoleService;
import com.radekdawid.petexpert.validation.registration.RegistrationValidator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final RegistrationValidator registrationValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ServicesService servicesService;

    public void registerUser(@NotNull UserRegistrationRequest request) {
        User newUser = createNewUser(request);
        String token = confirmationTokenService.createToken(newUser);

        emailService.buildRegistrationEmail(request.getFirstName(), request.getEmail(), token);
        userRepository.save(newUser);
    }

    public void registerProvider(@NotNull ProviderRegistrationRequest request) {
        User newUser = createNewProvider(request);
        String token = confirmationTokenService.createToken(newUser);
        emailService.buildRegistrationEmail(request.getFirstName(), request.getEmail(), token);
        userRepository.save(newUser);
    }

    @Transactional
    public void registerDefaultProvider(ProviderRegistrationRequest request, List<Company> companies, Socials socials) {
        User newProvider = createNewProvider(request);
        newProvider.setEnabled(true);
        newProvider.setSocials(socials);

        userRepository.save(newProvider);

        if(companies!= null) {
            for (Company company : companies) {
                newProvider.addCompany(company);
            }
        }
    }

    @NotNull
    private User createNewUser(UserRegistrationRequest request) {
        Long userRoleNumber = 1L;

        registrationValidator.userExistingChecker(request.getEmail());
        registrationValidator.passwordChecker(request.getPassword());

        User newUser = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword());

        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        newUser.addRole(roleService.getRole(userRoleNumber));
        return newUser;
    }

    @NotNull
    private User createNewProvider(@NotNull ProviderRegistrationRequest request) {
        Long providerRoleNumber = 2L;

        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest(request.getFirstName(),
                request.getLastName(),request.getPassword(),request.getEmail());
        User newUser = createNewUser(userRegistrationRequest);
        newUser.addRole(roleService.getRole(providerRoleNumber));
        newUser.setAddress(new Address(request.getUserCity(), request.getUserStreet(), request.getUserNumber(),
                request.getUserLocal(), request.getUserZip()));
        newUser.addCompany(createUserCompany(request));
        newUser.setDetails(new Details(request.getNip(), request.getPhone(), newUser));
        addServicesToUser(newUser, request.getServices());

        return newUser;
    }

    private Company createUserCompany(@NotNull ProviderRegistrationRequest request){
        Company company = new Company(request.getCompanyName());
        Address companyAddress = new Address(request.getCompanyCity(), request.getCompanyStreet(), request.getCompanyNumber(), request.getCompanyLocal(), request.getCompanyZip());
        company.addAddress(companyAddress);
        return company;
    }

    private void addServicesToUser(User user, List<Long> services){
        for (Long serviceId: services) {
            user.addService(servicesService.getService(serviceId));
        }
    }

}






