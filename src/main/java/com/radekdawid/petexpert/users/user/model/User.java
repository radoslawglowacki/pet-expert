package com.radekdawid.petexpert.users.user.model;

import com.radekdawid.petexpert.users.user_address.model.UserAddress;
import com.radekdawid.petexpert.users.user_company.Company;
import com.radekdawid.petexpert.users.user_role.model.Role;
import com.radekdawid.petexpert.users.user_services.UserServices;
import com.radekdawid.petexpert.users.user_socials.UserSocials;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long user_id;

    @NotNull(message = "Name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Email(message = "Email is not valid")
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserAddress> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Company> companies = new HashSet<>();

    @OneToOne
    @JoinColumn
    private UserServices services;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<UserAddress> getUserAddresses(){
        return addresses;
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


}
