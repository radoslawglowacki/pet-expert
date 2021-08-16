package com.radekdawid.petexpert.users.company.controller;


import com.radekdawid.petexpert.users.company.dto.CompanyListDto;
import com.radekdawid.petexpert.users.company.dto.CompanyUpdateDto;
import com.radekdawid.petexpert.users.company.mapper.CompanyListMapper;
import com.radekdawid.petexpert.users.company.service.CompanyService;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1//company")
public class CompanyController {

    private final CompanyService companyService;

//    TODO change to token JWT and validate it
    @GetMapping("/list/{id}")
    public List<CompanyListDto> getUserCompaniesList(@PathVariable Long id) {
        return companyService.getUserCompaniesList(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CompanyUpdateDto companyUpdateDto){
        companyService.updateCompany(companyUpdateDto);
        return ResponseEntity.ok("updated");
    }
}
