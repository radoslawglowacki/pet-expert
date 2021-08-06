package com.radekdawid.petexpert.users.user.model;

import com.radekdawid.petexpert.security.jwt.refreshToken.RefreshToken;
import com.radekdawid.petexpert.users.address.model.Address;
import com.radekdawid.petexpert.users.company.model.Company;
import com.radekdawid.petexpert.users.details.model.Details;
import com.radekdawid.petexpert.users.role.model.Role;
import com.radekdawid.petexpert.users.services.model.Services;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @NotNull
    @NotBlank(message = "Name cannot be null")
    @Length(min = 2)
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name cannot be null")
    @Length(min = 2)
    private String lastName;

    @NotBlank
    @Email(message = "Email is not valid")
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Services> services = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Company> companies = new HashSet<>();

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private Details details;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private RefreshToken refreshToken;

    private boolean locked = false;
    private boolean enabled = false;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public void addRole(Role role) {
        roles.add(role);
    }

    public void addCompany(Company company) {
        companies.add(company);
    }

    public void addService(Services service){services.add(service);}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    //    TODO
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //    TODO
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public Long getId() {
        return id;
    }
}
