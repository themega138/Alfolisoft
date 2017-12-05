package com.mq310.alfolisoft.managers;

import com.mq310.ent.sec.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SecurityManager {

    private AuthenticationManager authenticationManager;

    public SecurityManager() {
    }

    public void setAuthenticationManager(AuthenticationManager auth) {
        this.authenticationManager = auth;
    }

    public Boolean authenticate(User user) throws AuthenticationException{
        Authentication request = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user
                .getPassword());
        @SuppressWarnings("unused")
		Authentication result = authenticationManager.authenticate(request);
        
        return false;
    }
}
