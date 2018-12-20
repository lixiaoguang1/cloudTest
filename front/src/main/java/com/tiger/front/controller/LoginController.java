package com.tiger.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * �û���¼ҳ��
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	/**
	 *用户登录
	 * @return
	 */
    @RequestMapping("/toLogin")
	public String toLogin() {
    	return "login";
    }
    
}
