package com.radekdawid.petexpert.users.company.controller;


import com.radekdawid.petexpert.users.company.dto.CompanyListDto;
import com.radekdawid.petexpert.users.company.mapper.CompanyListMapper;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1//company")
public class CompanyController {

    private final UserService userService;
    private final CompanyListMapper listMapper;

    @GetMapping("/list/{id}")
    public List<CompanyListDto> getUserCompaniesList(@PathVariable Long id) {
        return listMapper.map(userService.getUserCompanies(id));
    }


}
