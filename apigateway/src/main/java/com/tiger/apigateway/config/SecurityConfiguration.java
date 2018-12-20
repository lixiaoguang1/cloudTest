package com.tiger.apigateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tiger.apigateway.security.JwtAuthenticationTokenFilter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableConfigurationProperties(SecuritySettings.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	
	@Autowired
	private UrlAccessDecisionManager urlAccessDecisionManager;
	
	@Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
	
	@Value("${securityconfig.permitall}")
	private String [] permitall;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// .passwordEncoder(passwordEncoder())
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		 auth.inMemoryAuthentication()
//		 .withUser("admin").password("123456").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		 // 由于使用的是JWT，我们这里不需要csrf
        .csrf().disable()
        // 基于token，所以不需要session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()		
		.withObjectPostProcessor(new ObjectPostProcessor
				<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
				o.setAccessDecisionManager(urlAccessDecisionManager);
				return o;
			}
		})
//		.and()
//		.formLogin()
//		.loginPage("/tologin")
//		.loginProcessingUrl("/login").permitAll()
//		.failureHandler(new AuthenticationFailureHandler() {
//			@Override
//			public void onAuthenticationFailure(HttpServletRequest request, 
//					HttpServletResponse response, AuthenticationException e)
//					throws IOException, ServletException {
//				response.setContentType("application/json;charset=utf-8");
//	                PrintWriter out = response.getWriter();
//	                StringBuffer sb = new StringBuffer();
//	                sb.append("{\"status\":\"error\",\"msg\":\"");
//	                if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
//	                    sb.append("用户名或密码输入错误，登录失败!");
//	                } else if (e instanceof DisabledException) {
//	                    sb.append("账户被禁用，登录失败，请联系管理员!");
//	                } else {
//	                    sb.append("登录失败!");
//	                }
//	                sb.append("\"}");
//	                out.write(sb.toString());
//	                out.flush();
//	                out.close();
//			}
//		})
//		.successHandler(new AuthenticationSuccessHandler() {
//			@Override
//			public void onAuthenticationSuccess(
//					HttpServletRequest request, 
//					HttpServletResponse response,
//					Authentication arg2)
//					throws IOException, ServletException {
//				response.setContentType("application/json;charset=utf-8");
////                PrintWriter out = response.getWriter();
////                String s = "{\"status\":\"success\",\"msg\":\"登录成功！\"}";
////                out.write(s);
////                out.flush();
////                out.close();
//				response.sendRedirect("/index");
//			}
//		})
//			.and().authorizeRequests()
			    // 对于获取token的rest api要允许匿名访问
//				.antMatchers("/auth","/auth/**","/front/tologin","/front/styles/**",
//						    "/front/js/**",
//						    "/rest/**","/staff/register").permitAll()
				.antMatchers(permitall).permitAll()
				.anyRequest().authenticated();
//			.and().logout().permitAll().and().csrf().disable()
//			.exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
				//.requireCsrfProtectionMatcher(csrfSecurityRequestMatcher());
		// 禁用缓存
        http.headers().cacheControl();
//        
//      //添加JWT filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

//	private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher() {
//		CsrfSecurityRequestMatcher csrfSecurityRequestMatcher = new CsrfSecurityRequestMatcher();
//		List<String> list = new ArrayList<String>();
//		list.add("/rest/");
//		csrfSecurityRequestMatcher.setExecludeUrls(list);
//		return csrfSecurityRequestMatcher;
//	}

}

