package com.tiger.apigateway.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tiger.apigateway.dao.StaffMapper;
import com.tiger.apigateway.entity.Staff;
import com.tiger.apigateway.security.JwtUserFactory;




@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private StaffMapper staffInfoMapper;
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	logger.info("查询当前登录用户信息开始！"+username);
    	Staff staffInfo=staffInfoMapper.selectByStaffNo(username);
    	logger.info("staffInfo:{}",staffInfo);
        return JwtUserFactory.create(staffInfo);
	}


//	private Logger logger=LoggerFactory.getLogger(this.getClass());
//	    @Override
//	    public UserDetails loadUserByUsername(String userName) 
//	    		throws UsernameNotFoundException {
//	    	logger.info("查询当前登录用户信息开始！"+userName);
//	    	StaffInfo staffInfo=staffInfoMapper.selectByStaffNo(userName);
//	    	logger.info("staffInfo:{}",staffInfo);
//	        return new SecurityUser(staffInfo);
//	    }

}