package com.tiger.apigateway.controller;


import java.util.UUID;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiger.apigateway.entity.Staff;
import com.tiger.apigateway.redis.service.IRedisService;
import com.tiger.apigateway.security.JwtAuthenticationRequest;
import com.tiger.apigateway.security.JwtAuthenticationResponse;
import com.tiger.apigateway.service.AuthService;



@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthService authService;
    
    @Autowired
    private IRedisService<String, String> redisService;
    
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
    		@RequestBody JwtAuthenticationRequest authenticationRequest) 
            		throws AuthenticationException{
    	logger.info("用户登录:{}",authenticationRequest.getUsername());
        final String token = authService.login(authenticationRequest.getUsername(), 
        		authenticationRequest.getPassword());
        String code="";
        String token_key="";
        if(null !=token && token.length()>0) {
        	code="200000";
        	token_key=UUID.randomUUID().toString();
        	redisService.add(token_key, token);
        	logger.info(token_key+":"+redisService.get(token_key));
        }else {
        	code="400405";
        }
        return ResponseEntity.ok(new JwtAuthenticationResponse(token_key,code));
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
    public Staff register(@RequestBody Staff addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }
}

