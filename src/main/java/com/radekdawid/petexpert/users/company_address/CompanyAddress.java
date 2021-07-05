package com.radekdawid.petexpert.users.company_address;


import com.radekdawid.petexpert.users.user_company.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "company_address")
@NoArgsConstructor
public class CompanyAddress {

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Company company;

    public CompanyAddress(@NotNull(message = "City cannot be null") String city, @NotNull(message = "Street cannot be null") String street, @NotNull(message = "Number cannot be null") String number, String local, @NotNull(message = "Zip code cannot be null") String zip, Company company) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.local = local;
        this.zip = zip;
        this.company = company;
    }
}
