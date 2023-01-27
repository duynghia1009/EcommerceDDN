package com.DDN.login.dto;

public class StripeResponse {

    private String clientSecret;


    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }



    public StripeResponse(String clientSecret) {
            this.clientSecret = clientSecret;
    }

    public StripeResponse() {
    }
}
