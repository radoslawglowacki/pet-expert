package com.radekdawid.petexpert.users.details.mapper;


import com.radekdawid.petexpert.users.details.dto.DetailsDto;
import com.radekdawid.petexpert.users.details.model.Details;
import org.springframework.stereotype.Component;

@Component
public class DetailsMapper {

    public DetailsDto map(Details details){
        return new DetailsDto(details.getId(), details.getNip(), details.getPhone(), details.getDescription());
    }
}
