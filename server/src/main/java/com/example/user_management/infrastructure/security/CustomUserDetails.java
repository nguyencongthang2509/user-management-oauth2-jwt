package com.example.user_management.infrastructure.security;

import com.example.user_management.infrastructure.constant.ActorConstants;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ToString
public class CustomUserDetails implements UserDetails {

    private UserDetails userDetails;

    private UUID id;
    private String fullName;
    private String role;

    public CustomUserDetails(UserDetails userDetails, UUID id, String fullName, String role) {
        this.userDetails = userDetails;
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (role) {
            case ActorConstants.MENTOR:
                authorities.add(new SimpleGrantedAuthority("MENTOR"));
                break;
            case ActorConstants.INTERN:
                authorities.add(new SimpleGrantedAuthority("INTERN"));
                break;
            default:
                break;
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
