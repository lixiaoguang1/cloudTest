package com.tiger.apigateway.config;



import java.util.Collection;
import java.util.Iterator;

import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 权限管理决断器
 * @author Administrator
 *
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager{

	private org.slf4j.Logger logger =LoggerFactory.getLogger(this.getClass());
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		Iterator<ConfigAttribute> iterator=configAttributes.iterator();
		while(iterator.hasNext()) {
			ConfigAttribute ca=iterator.next();
			String needRole=ca.getAttribute();
			
			logger.info("访问当前服务需要的角色信息:{}",needRole);
			if("ROLE_LOGIN".equals(needRole)) {
				if(authentication instanceof AnonymousAuthenticationToken) {
					throw new BadCredentialsException("未登录");
				}else {
					return ;
				}
			}
			 //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities 
               = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
            	logger.info("current user role:{}",grantedAuthority.getAuthority());
				if(grantedAuthority.getAuthority().equals(needRole)) {
					return ;
				}
				logger.info("current user role {} is not service",grantedAuthority.getAuthority());
			}
            throw new AccessDeniedException("权限不足");
		}
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
