package com.tiger.apigateway.config;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.tiger.apigateway.dao.ServiceMapper;
import com.tiger.apigateway.entity.Service;
import com.tiger.apigateway.redis.service.IRedisService;


@ConfigurationProperties(prefix="securityconfig")
public class SecuritySettings {
	
	private final org.slf4j.Logger logger=LoggerFactory.getLogger(this.getClass());
    private String logoutsuccssurl = "/logout";
    private String permitall = "/api";
    private String deniedpage = "/deny";
    private String urlroles;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private IRedisService<String, String> redisService;

    public String getLogoutsuccssurl() {
        return logoutsuccssurl;
    }

    public void setLogoutsuccssurl(String logoutsuccssurl) {
        this.logoutsuccssurl = logoutsuccssurl;
    }

    public String getPermitall() {
        return permitall;
    }

    public void setPermitall(String permitall) {
        this.permitall = permitall;
    }

    public String getDeniedpage() {
        return deniedpage;
    }

    public void setDeniedpage(String deniedpage) {
        this.deniedpage = deniedpage;
    }

    public String getUrlroles() {
        return redisService.get("service:role");
    }

    public void setUrlroles(String urlroles) {
    	logger.info("加载权限角色信息begin");
    	List<Service> list=serviceMapper.getServices();
    	StringBuffer sb=new StringBuffer();
    	for (Service service : list) {
    		
    		sb.append(service.getMethod()+"@"+service.getUrl()+"=");
    		StringBuffer sb1=new StringBuffer();
    		List<String> roles=service.getRoles();
    		for (String role : roles) {
				sb1.append(role+",");
			}
    		String tempRole=sb1.toString().substring(0, sb1.toString().length()-1);
    		sb.append(tempRole+";");
    		
		}
    	logger.info("service:role:{}",sb.toString());
    	redisService.add("service:role", sb.toString());
        //this.urlroles = "/**/new/** = admin111;/**/edit/** = admin,editor111;";
    }
}