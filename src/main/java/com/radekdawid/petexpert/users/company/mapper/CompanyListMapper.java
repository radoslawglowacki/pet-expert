package com.radekdawid.petexpert.users.company.mapper;


import com.radekdawid.petexpert.users.company.dto.CompanyListDto;
import com.radekdawid.petexpert.users.company.model.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyListMapper {

    public List<CompanyListDto> map(List<Company> companyList){
        ArrayList<CompanyListDto> result = new ArrayList<>();

        for (Company ele: companyList) {
                result.add(new CompanyListDto(ele.getId(), ele.getName()));
        }
        return result;
    }
}
