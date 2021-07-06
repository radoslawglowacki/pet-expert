package com.radekdawid.petexpert.users.address.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "City cannot be null")
    private String city;
    @NotNull(message = "Street cannot be null")
    private String street;
    @NotNull(message = "Number cannot be null")
    private String number;
    private String local;
    @NotNull(message = "Zip code cannot be null")
    private String zip;


    public Address(String city, String street, String number, String local, String zip) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.local = local;
        this.zip = zip;
    }
}
