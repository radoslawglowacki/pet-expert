package com.radekdawid.petexpert.users.company.controller;


import com.radekdawid.petexpert.users.company.dto.CompanyListDto;
import com.radekdawid.petexpert.users.company.dto.CompanyUpdateDto;
import com.radekdawid.petexpert.users.company.mapper.CompanyListMapper;
import com.radekdawid.petexpert.users.company.service.CompanyService;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1//company")
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping("/list")
    public List<CompanyListDto> getUserCompaniesList(HttpServletRequest request) {
        return companyService.getUserCompaniesList(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CompanyUpdateDto companyUpdateDto, HttpServletRequest request){
        companyService.updateCompany(companyUpdateDto, request);
        return ResponseEntity.ok("updated");
    }
}
