package com.radekdawid.petexpert.users.user.controller;

import com.radekdawid.petexpert.users.offer.dto.OfferDto;
import com.radekdawid.petexpert.users.user.dto.UserPageDto;
import com.radekdawid.petexpert.users.user.reguests.UserPageRequest;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/page")
//    @PreAuthorize("hasRole({'PROVIDER', 'USER'})")
//    @PreAuthorize("hasRole('PROVIDER')" + " || hasRole('USER')")
//    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<UserPageDto> getUserDetailsPage(@Valid @RequestBody UserPageRequest request) {
        return new ResponseEntity<>(userService.getUserDetailsPage(request.getToken()), HttpStatus.OK);
    }

}