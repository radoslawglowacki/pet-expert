package com.radekdawid.petexpert.users.company.model;


import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.offer.model.Offer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "company")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Name can not be null")
    private String name;

    @Length(min = 2, max = 3000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Offer> offers = new HashSet<>();

    public Company(String name) {
        this.name = StringUtils.capitalize(name.toLowerCase());
    }

    public Company(@NotNull @NotBlank(message = "Name can not be null") String name, @Length(min = 2, max = 3000) String description) {
        this.name = name;
        this.description = description;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}
