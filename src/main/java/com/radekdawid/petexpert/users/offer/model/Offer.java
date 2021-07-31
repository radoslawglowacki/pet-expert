package com.radekdawid.petexpert.users.offer.model;


import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "offers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Price cannot be null")
    private Long price;

    @NotNull
    private LocalDate created_at;

    @NotNull
    private boolean drivingToClient;

    @NotNull
    private Long drivingRadius = 0L;

    @NotNull
    private String city;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private Services service;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Company company;


//    TODO: add rating
//    TODO: photos AWS


    public Offer(@NotNull(message = "Name cannot be null") String name, @NotNull(message = "Description cannot be null") String description, @NotNull(message = "Price cannot be null") Long price,
                 @NotNull boolean drivingToClient, Long drivingRadius, String city, Services service, Company company) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.created_at = LocalDate.now();
        this.drivingToClient = drivingToClient;
        this.drivingRadius = drivingRadius;
        this.service = service;
        this.company = company;
        this.city = city;
    }

}
