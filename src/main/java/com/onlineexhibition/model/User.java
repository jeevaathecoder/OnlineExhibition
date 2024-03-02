package com.onlineexhibition.model;

import jakarta.persistence.*;
import java.util.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@Entity
@Table(name = "user")
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="first_name")
    private String firstname;
    @Column(name="last_name")
    private String lastname;
    @Column(name="user_email",nullable = false)
    private String email;
    @Column(name="user_password")
    private String password;
    @Column(name = "Contact")
    private String mobile;

    @Column(name="user_status")
    private String status;
    @Column(name="user_type")
    private Long user_type_id;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;//TO-DO add the role here
    }

    @Override
    public String getUsername() {
        return this.email;
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
