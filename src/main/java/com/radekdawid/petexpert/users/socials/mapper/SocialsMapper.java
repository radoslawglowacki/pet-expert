package com.radekdawid.petexpert.users.socials.mapper;

import com.radekdawid.petexpert.users.socials.dto.SocialsDto;
import com.radekdawid.petexpert.users.socials.model.Socials;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SocialsMapper {

    public SocialsDto map(Socials socials){
        return new SocialsDto(socials.getId(),socials.getFacebook(),
                socials.getInstagram(),socials.getTwitter(),socials.getWebsite());
    }
}
