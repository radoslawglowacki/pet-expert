package com.radekdawid.petexpert.users.user_company;


import com.radekdawid.petexpert.users.company_address.CompanyAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_company")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name can not be null")
    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CompanyAddress> addresses = new HashSet<>();

    public Company(String name) {
        this.name = name;
    }

    public void addAddress(CompanyAddress address){
        addresses.add(address);
    }
}
