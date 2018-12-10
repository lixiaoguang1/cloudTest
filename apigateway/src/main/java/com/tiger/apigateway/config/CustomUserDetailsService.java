package com.tiger.apigateway.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tiger.apigateway.entity.StaffInfo;
import com.tiger.apigateway.security.JwtUserFactory;




@Component
public class CustomUserDetailsService implements UserDetailsService {

//	@Autowired
//	private StaffInfoMapper staffInfoMapper;
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	logger.info("查询当前登录用户信息开始！"+username);
    	StaffInfo staffInfo=new StaffInfo();
//    	StaffInfo staffInfo=staffInfoMapper.selectByStaffNo(username);
    	staffInfo.setUsername("admin");
    	staffInfo.setPassword(new BCryptPasswordEncoder().encode("123456"));
    	logger.info("staffInfo:{}",staffInfo);
    	List<String> roles=new ArrayList<>();
    	roles.add("admin");
    	staffInfo.setRoles(roles);
//       /* return new JwtUser(staffInfo.getOrderId(),
//        		staffInfo.getStaffNo(),staffInfo.getStaffPwd(),null,null,new Date());*/
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