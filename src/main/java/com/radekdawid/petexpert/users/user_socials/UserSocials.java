package com.radekdawid.petexpert.users.user_socials;


import com.radekdawid.petexpert.users.user_details.UserDetails;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_socials")
public class UserSocials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String facebook;
    private String instagram;
    private String twitter;
    private String website;
    @OneToOne(mappedBy = "socials")
    private UserDetails user;
}
