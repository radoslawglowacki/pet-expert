package com.radekdawid.petexpert.users.user_details;


import com.radekdawid.petexpert.users.user_socials.UserSocials;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user_details")
@NoArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 10, max = 10)
    @NotNull
    private int nip;
    @Length(min = 9)
    private int phone;
    private String description;
    @OneToOne
    @JoinColumn
    private UserSocials socials;

    public UserDetails(@NotNull @Length(min = 10, max = 10) @NotNull int nip, @Length(min = 9) int phone) {
        this.nip = nip;
        this.phone = phone;
    }
}
