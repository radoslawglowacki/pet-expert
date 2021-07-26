package com.radekdawid.petexpert.users.services.model;


import com.radekdawid.petexpert.users.user.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "services")
@NoArgsConstructor
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean groomer = false;
    private boolean vet = false;
    private boolean petsitter = false;
    private boolean behaviorist = false;
    @OneToOne(mappedBy = "services", fetch = FetchType.EAGER)
    private User user;

    public Services(boolean groomer, boolean vet, boolean petsitter, boolean behaviorist, User user) {
        this.groomer = groomer;
        this.vet = vet;
        this.petsitter = petsitter;
        this.behaviorist = behaviorist;
        this.user = user;
    }
}
