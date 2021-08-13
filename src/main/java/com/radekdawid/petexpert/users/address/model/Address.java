package com.radekdawid.petexpert.users.address.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Locale;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank(message = "City cannot be null")
    @Length(min = 2, max = 30)
    private String city;

    @NotNull
    @NotBlank(message = "Street cannot be null")
    @Length(min = 2, max = 30)
    private String street;

    @NotNull
    @NotBlank(message = "Number cannot be null")
    @Pattern(regexp = "^[0-9]")
    private String number;

    @Pattern(regexp = "^[0-9]*$")
    private String local;

    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
    @NotNull
    @NotBlank(message = "Zip code cannot be null")
    private String zip;


    public Address(String city, String street, String number, String local, String zip) {
        this.city = StringUtils.capitalize(city.toLowerCase());
        this.street = StringUtils.capitalize(street.toLowerCase());
        this.number = number;
        this.local = local;
        this.zip = zip;

    }
}
