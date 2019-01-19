package com.tiger.apigateway.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tiger.apigateway.redis.service.IRedisService;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Value("${jwt.header}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${securityconfig.permitall}")
	private String [] permitall;
	
	@Autowired
	private IRedisService<String, String> redisService;
	
	private Logger logger=LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader(this.tokenHeader);
		String reqUrl=request.getRequestURI();
		logger.info("当前请求url:{}",reqUrl);
		logger.info("免登录可以访问的url信息:");
		PathMatcher pathMathcher=new AntPathMatcher();
	    for(String url:permitall) {
	    	logger.info("url:{}",url);
	    	if(pathMathcher.match(url, reqUrl)) {
	    		chain.doFilter(request, response);
				return ;
	    	}
	    }

//		if(request.getRequestURI().contains("/auth")
//				||request.getRequestURI().contains("/front/tologin")
//				||request.getRequestURI().contains("/front/styles")
//				||request.getRequestURI().contains("/front/scripts")
//				||request.getRequestURI().contains("/front/images")){
//			
//		}
	    logger.info("authHeader:{}",authHeader);
		String token=(String)redisService.get(authHeader);
		if (token != null) {
			//final String authToken = authHeader.substring(tokenHead.length());
			// The part after "Bearer "
			String username = jwtTokenUtil.getUsernameFromToken(token);
			logger.info("checking authentication " + username);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				if (jwtTokenUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					logger.info("authenticated user " + username + ", setting security context");
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}else {
			response.sendError(401, "当前用户没有登录或者登录的token信息不正确");
			return ;
			//throw new BadCredentialsException("未登录");
		}
		chain.doFilter(request, response);
	}
}
