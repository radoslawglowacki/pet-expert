package com.radekdawid.petexpert.users.user.reguests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserPageRequest {

    @NotBlank
    private String token;


}