package com.tiger.apigateway.service;

import com.tiger.apigateway.entity.User;

public interface AuthService {
	User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
