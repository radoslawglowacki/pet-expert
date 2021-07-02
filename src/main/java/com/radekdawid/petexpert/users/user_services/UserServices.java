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
    private boolean groomer = false;
    private boolean vet = false;
    private boolean petsitter = false;
    private boolean behaviorist = false;
    @OneToOne(mappedBy = "services")
    private User user;
}
