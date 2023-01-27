package com.DDN.login.security.service.auth;

import com.DDN.login.model.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface AuthDataService {
    User findByUsername(String username);

    User findByEmail(String email);

    void deleteByUsernamePassword(String username, String password) throws NoSuchAlgorithmException;

    void createUserProfile(User user) throws NoSuchAlgorithmException;

}
