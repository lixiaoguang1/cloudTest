package com.tiger.apigateway.config;



import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 *权限管理过滤器
 * @author Administrator
 *
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource 
    implements FilterInvocationSecurityMetadataSource {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private  SecuritySettings securitySettings;
	@Value("${securityconfig.permitall}")
	private String [] permitall;
	
	private PathMatcher pathMatcher=new AntPathMatcher();
	
	

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String urlroles=securitySettings.getUrlroles();
		logger.info("urlsroles:{}",urlroles);
		//获取当前的请求地址
		//String method=((FilterInvocation)object).getHttpRequest().getMethod().toLowerCase();
		String requestUrl=((FilterInvocation)object).getHttpRequest().getMethod().toLowerCase()
				+"@"+((FilterInvocation)object).getHttpRequest().getRequestURI();
		
		logger.info("====当前请求地址url:"+
		((FilterInvocation)object).getHttpRequest().getRequestURI());
		logger.info("====当前请求地址url:"+requestUrl);
		
	    for(String url:permitall) {
	    	if(pathMatcher.match(url, ((FilterInvocation)object).getHttpRequest().getRequestURI())) {
	    		return null;
	    	}
	    }
		
//		if(requestUrl.contains("/auth") 
//				||requestUrl.contains("front/styles/") 
//				||requestUrl.contains("/images/")
//				||requestUrl.contains("/front/scripts/")
//				||"/front/tologin".equals(requestUrl)
//				||"/staff/register".equals(requestUrl)) {
//			return null;
//		}
		String [] arraysUrls=urlroles.split(";");
		logger.info("??????"+pathMatcher.match("get@/systemManager/system/v1/staff/searchStaff",
				requestUrl));
		for (String string : arraysUrls) {
			String [] urlrole=string.split("=");
			logger.info("urlrole:{},requestUrl:{}",urlrole[0],requestUrl);
			if(pathMatcher.match(urlrole[0].trim(),requestUrl)) {
				logger.info("=================urlrole:{}",urlrole[0]);
				return SecurityConfig.createList(urlrole[1]);
			}
		}
		logger.info("==============================================!");
		//没有匹配上的资源，都是登录访问  "ROLE_LOGIN"
        return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}