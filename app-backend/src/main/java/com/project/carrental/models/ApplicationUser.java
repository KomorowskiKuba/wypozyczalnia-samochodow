package com.project.carrental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotEmpty
    //@Size(min = 1, max = 100)
    private String firstName;

    //@NotEmpty
    //@Size(min = 1, max = 100)
    private String lastName;

    //@NotEmpty
    //@Email
    private String email;

    //@NotEmpty
    //@Size(min = 1, max = 20)
    private String username;

    //@NotEmpty
    //@Size(min = 8, max = 100)
    private String password;

    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billingDetails_id", referencedColumnName = "id")
    private BillingDetails billingDetails;

    public ApplicationUser(String firstName, String lastName, String email, String username, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
