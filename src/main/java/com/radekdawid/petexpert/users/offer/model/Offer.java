package com.radekdawid.petexpert.users.offer.model;


import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.services.model.Services;
import com.radekdawid.petexpert.users.user.model.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotNull
    @NotBlank(message = "Name cannot be null")
    @Length(min = 2, max = 30)
    private String name;

    @NotNull
    @NotBlank(message = "Description cannot be null")
    @Length(min = 2, max = 3000)
    private String description;

    @NotNull(message = "Price cannot be null")
    @Range(max = 10000)
    private Long price;

    private LocalDate created_at;

    @NotNull
    private boolean drivingToClient;

    @NotNull
    @Range(max = 200L)
    private Long drivingRadius = 0L;

    @NotNull
    @Length(min = 2, max = 30)
    private String city;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private Services service;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Company company;


    public Offer(@NotNull(message = "Name cannot be null") String name, @NotNull(message = "Description cannot be null") String description, @NotNull(message = "Price cannot be null") Long price,
                 @NotNull boolean drivingToClient, Long drivingRadius, String city, Services service, Company company) {
        this.name = StringUtils.capitalize(name.toLowerCase());
        this.description = description;
        this.price = price;
        this.created_at = LocalDate.now();
        this.drivingToClient = drivingToClient;
        this.drivingRadius = drivingRadius;
        this.service = service;
        this.company = company;
        this.city = StringUtils.capitalize(city.toLowerCase());;
    }

}
