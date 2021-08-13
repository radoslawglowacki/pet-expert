package com.radekdawid.petexpert.users.user.mapper;


import com.radekdawid.petexpert.security.jwt.utils.JwtUtils;
import com.radekdawid.petexpert.users.company.dto.CompanyDto;
import com.radekdawid.petexpert.users.company.mapper.CompanyMapper;
import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.details.mapper.DetailsMapper;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.socials.mapper.SocialsMapper;
import com.radekdawid.petexpert.users.user.dto.UserPageDto;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;

@Component
public class UserPageMapper {


    private final UserService service;
    private final CompanyMapper companyMapper;
    private final DetailsMapper detailsMapper;
    private final SocialsMapper socialsMapper;
    private final JwtUtils jwtUtils;

    public UserPageMapper(@Lazy UserService service, CompanyMapper companyMapper, DetailsMapper detailsMapper,
                          SocialsMapper socialsMapper, JwtUtils jwtUtils) {
        this.service = service;
        this.companyMapper = companyMapper;
        this.detailsMapper = detailsMapper;
        this.socialsMapper = socialsMapper;
        this.jwtUtils = jwtUtils;
    }

    public UserPageDto map(String token) {
        if (jwtUtils.validateJwtToken(token)) {
            User userById = service.getUserById(jwtUtils.getUserIdFromJwtToken(token));

            ArrayList<Services> services = new ArrayList<>(userById.getServices());
            services.sort(Comparator.comparing(Services::getName));

            ArrayList<CompanyDto> companyDtos = new ArrayList<>();

            for (Company company : userById.getCompanies()) {
                companyDtos.add(companyMapper.map(company));
            }

            return new UserPageDto(userById.getFirstName(), userById.getLastName(), userById.getEmail(),
                    detailsMapper.map(userById.getDetails()), userById.getAddress(),
                    socialsMapper.map(userById.getSocials()), services, companyDtos);
        }
        throw new RuntimeException("Can't create new User Page");
    }
}
