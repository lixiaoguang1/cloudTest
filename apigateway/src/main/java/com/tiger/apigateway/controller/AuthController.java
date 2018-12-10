package com.tiger.apigateway.controller;


import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiger.apigateway.entity.User;
import com.tiger.apigateway.security.JwtAuthenticationRequest;
import com.tiger.apigateway.security.JwtAuthenticationResponse;
import com.tiger.apigateway.service.AuthService;



@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthService authService;
    
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
    		@RequestBody JwtAuthenticationRequest authenticationRequest) 
            		throws AuthenticationException{
    	System.out.println("????????????????????????????????:"+authenticationRequest.toString());
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    	//final String token = authService.login("admin", "123456");
        String code="";
        if(null !=token && token.length()>0) {
        	code="200000";
        }else {
        	code="400405";
        }
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token,code));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken,""));
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public User register(@RequestBody User addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }
}

