package com.DDN.login.dto.auth;

public class AuthenticationResponse {
    private String jwt;
    private String error;
    private Long userId;

    public AuthenticationResponse(String jwt, String error, Long userId){
        this.jwt = jwt;
        this.error = error;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AuthenticationResponse() {}

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}
