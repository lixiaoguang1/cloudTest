package com.tiger.apigateway.security;



import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;
    
    private final String code;
    private final String token;

    public JwtAuthenticationResponse(String token,String code) {
        this.token = token;
        this.code=code;
    }

    public String getToken() {
        return this.token;
    }
    public String getCode() {
    	return this.code;
    }
}
