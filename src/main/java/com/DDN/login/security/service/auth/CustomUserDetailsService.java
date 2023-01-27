package com.DDN.login.security.service.auth;

import com.DDN.login.model.User;
import com.DDN.login.repository.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserReposity userReposity;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userReposity.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), Arrays.asList());

    }
}
