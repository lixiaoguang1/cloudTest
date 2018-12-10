package com.tiger.apigateway.security;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tiger.apigateway.entity.StaffInfo;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(StaffInfo user) {
    	System.out.println(user.toString());
        return new JwtUser(
                user.getOrderId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
