package com.tiger.apigateway.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiger.apigateway.dao.StaffMapper;
import com.tiger.apigateway.entity.Staff;
import com.tiger.apigateway.security.JwtTokenUtil;
import com.tiger.apigateway.security.JwtUser;



@Service
public class AuthServiceImpl implements AuthService {

	
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private StaffMapper staffMapper;
    
    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
       //     AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil) {
       // this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Staff register(Staff userToAdd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getStaffPwd();
        userToAdd.setStaffPwd(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        List<String> roles=new ArrayList<>();
        userToAdd.setRoles(roles);
        staffMapper.insert(userToAdd);
        return  userToAdd;
    }

    @Override
    public String login(String username, String password) {
    	logger.info("调用用户登录服务，产生Token信息");
        //UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        //final Authentication authentication = authenticationManager.authenticate(upToken);
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        logger.info("Token Info:{}",token);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
//            return jwtTokenUtil.refreshToken(token);
//        }
        return jwtTokenUtil.refreshToken(token);
//        return null;
    }

	
}
