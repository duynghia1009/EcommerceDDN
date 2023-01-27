package com.DDN.login.security.service.auth;

import com.DDN.login.model.User;
import com.DDN.login.repository.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

public class AuthDataServiceImpl implements AuthDataService{
    @Autowired
    private UserReposity userReposity;




    @Override
    public User findByUsername(String username) {


        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void deleteByUsernamePassword(String username, String password) throws NoSuchAlgorithmException {

    }

    @Override
    public void createUserProfile(User user) throws NoSuchAlgorithmException {

    }
}
