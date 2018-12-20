package com.tiger.apigateway.service;

import com.tiger.apigateway.entity.Staff;

public interface AuthService {
	Staff register(Staff userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
