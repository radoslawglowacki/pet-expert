package com.radekdawid.petexpert.users.user_address.model;


import com.radekdawid.petexpert.users.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user_address")
@NoArgsConstructor
public class UserAddress {

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
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    public UserAddress(String city, String street, String number, String local, String zip, User user) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.local = local;
        this.zip = zip;
        this.user = user;
    }
}
