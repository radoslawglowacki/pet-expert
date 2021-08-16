package com.radekdawid.petexpert.users.address.controller;


import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.address.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Address address){
        addressService.update(address);
        return ResponseEntity.ok("updated");
    }
}
