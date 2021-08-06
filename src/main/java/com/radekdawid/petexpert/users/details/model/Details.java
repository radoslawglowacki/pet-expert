package com.radekdawid.petexpert.users.details.model;


import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.socials.model.Socials;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "details")
@NoArgsConstructor
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long nip;

    @NotNull
    private Long phone;

    @Length(min = 3)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Socials socials;

    @OneToOne(mappedBy = "details", cascade = CascadeType.ALL)
    private User user;

    public Details(@NotNull @Length(min = 10, max = 10) @NotNull Long nip, @Length(min = 9) Long phone, User user) {
        this.nip = nip;
        this.phone = phone;
        this.user = user;
    }
}
