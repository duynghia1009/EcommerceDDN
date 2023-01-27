package com.DDN.login.security.service;

import com.DDN.login.config.MessageStrings;
import com.DDN.login.repository.TokenRepository;
import com.DDN.login.exception.AuthenticationFailException;
import com.DDN.login.model.AuthenticationToken;
import com.DDN.login.model.User;
import com.DDN.login.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository repository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken){
        repository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user){
        return repository.findTokenByUser(user);
    }

    public User getUser(String token){
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if(Helper.notNull(authenticationToken)){
            if (Helper.notNull(authenticationToken.getUser())){
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if(Helper.notNull(token)){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if (Helper.notNull(getUser(token))){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }
}
