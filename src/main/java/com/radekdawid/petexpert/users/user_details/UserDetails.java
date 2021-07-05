package com.radekdawid.petexpert.users.user_details;


import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user_socials.UserSocials;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
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
    private Long nip;
    @NotNull
    private Long phone;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private UserSocials socials;

    @OneToOne(mappedBy = "details", cascade = CascadeType.ALL)
    private User user;

    public UserDetails(@NotNull @Length(min = 10, max = 10) @NotNull Long nip, @Length(min = 9) Long phone, User user) {
        this.nip = nip;
        this.phone = phone;
        this.user = user;
    }
}
