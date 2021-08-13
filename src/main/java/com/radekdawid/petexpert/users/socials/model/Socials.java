package com.radekdawid.petexpert.users.socials.model;


import com.radekdawid.petexpert.users.user.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "socials")
@NoArgsConstructor
public class Socials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facebook;

    private String instagram;

    private String twitter;

    private String website;

    @OneToOne(mappedBy = "socials")
    private User user;

    public Socials(String facebook, String instagram, String twitter, String website) {
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.website = website;
    }

}
