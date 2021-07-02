package com.radekdawid.petexpert.users.user_details;


import com.radekdawid.petexpert.users.user_socials.UserSocials;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_details")
@NoArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nip;
    private String description;

    @OneToOne
    @JoinColumn
    private UserSocials socials;

}
