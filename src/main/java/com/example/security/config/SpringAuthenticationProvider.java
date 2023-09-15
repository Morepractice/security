package com.example.security.config;

import com.example.security.service.UsersService;
import lombok.var;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SpringAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;

    private final UsersService usersService;

    public SpringAuthenticationProvider(PasswordEncoder passwordEncoder, UsersService usersService) {
        this.passwordEncoder = passwordEncoder;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = usersService.loadUserByUsername(authentication.getName());
        if(passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), user.getAuthorities());
        }else {
            throw new BadCredentialsException("The username or password is wrong!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
