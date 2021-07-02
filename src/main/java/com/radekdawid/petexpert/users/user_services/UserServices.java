package com.radekdawid.petexpert.users.user_services;


import com.radekdawid.petexpert.users.user.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_services")
public class UserServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean groomer;
    private boolean vet;
    private boolean petsitter;
    private boolean hotel;
    @OneToOne(mappedBy = "services")
    private User user;
}
