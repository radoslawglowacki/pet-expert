package com.radekdawid.petexpert.users.socials.model;


import com.radekdawid.petexpert.users.details.model.Details;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "socials")
public class Socials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String facebook;

    private String instagram;

    private String twitter;

    private String website;

    @OneToOne(mappedBy = "socials")
    private Details user;
}
