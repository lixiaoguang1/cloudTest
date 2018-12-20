package com.tiger.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 操作员页面
 * @author Administrator
 *
 */
@Controller
public class StaffController {
	@RequestMapping(value= {"/index","/staff/index"})
    public String toIndex(ModelMap model) {
		model.addAttribute("username", "test001");
		return "/system/staff/index";
	}
}
