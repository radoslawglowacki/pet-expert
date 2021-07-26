package com.radekdawid.petexpert.users.offer.model;


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

    private Long drivingRadius;

//    TODO: add rating
//    TODO: photos AWS


    public Offer(@NotNull(message = "Name cannot be null") String name, @NotNull(message = "Description cannot be null") String description, @NotNull(message = "Price cannot be null") Long price, @NotNull LocalDate created_at, @NotNull boolean drivingToClient, Long drivingRadius) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.created_at = LocalDate.now();
        this.drivingToClient = drivingToClient;
        this.drivingRadius = drivingRadius;
    }
}
