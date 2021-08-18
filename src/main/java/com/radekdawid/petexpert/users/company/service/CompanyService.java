package com.radekdawid.petexpert.users.company.service;


import com.radekdawid.petexpert.security.jwt.utils.JwtUtils;
import com.radekdawid.petexpert.users.company.dto.CompanyListDto;
import com.radekdawid.petexpert.users.company.dto.CompanyUpdateDto;
import com.radekdawid.petexpert.users.company.mapper.CompanyListMapper;
import com.radekdawid.petexpert.users.company.mapper.CompanyMapper;
import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.company.repository.CompanyRepository;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final CompanyListMapper listMapper;
    private final CompanyMapper companyMapper;
    private final JwtUtils jwtUtils;

    public Company getCompany(Long id) {
        Optional<Company> companyById = companyRepository.getCompanyById(id);

        if (companyById.isEmpty()) {
            throw new IllegalStateException("That company does not exists");
        }
        return companyById.get();
    }

    public List<CompanyListDto> getUserCompaniesList(HttpServletRequest request) {
        String token = jwtUtils.parseJwt(request);
        Long userIdFromJwtToken = jwtUtils.getUserIdFromJwtToken(token);
        return listMapper.map(userService.getUserCompanies(userIdFromJwtToken));
    }

    @Transactional
    public void updateCompany(CompanyUpdateDto companyUpdateDto, HttpServletRequest request) {
        companyRepository.save(companyMapper.mapToUpdate(companyUpdateDto));
    }
}
