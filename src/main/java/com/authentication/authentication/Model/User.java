package com.authentication.authentication.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiration;

    private boolean enabled;

    public User() {

    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Manage user roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // Is account still valid (not expired) ?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // is account locked due to too many failed attempts ?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    // Checks if user credentials (email, password) are correct
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Checks if a user is enabled - do they permission to login
    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
