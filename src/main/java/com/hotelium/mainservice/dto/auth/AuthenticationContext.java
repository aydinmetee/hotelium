package com.hotelium.mainservice.dto.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
@AllArgsConstructor
public class AuthenticationContext implements Authentication {

    private String token;
    private boolean authenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return new Object();
    }

    @Override
    public Object getDetails() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return new Object();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return null;
    }

}
