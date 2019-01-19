package com.tiger.front.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 操作员页面
 * @author Administrator
 *
 */
@Controller
public class StaffController {
	
	
	@RequestMapping(value= {"/index","/staff/index"})
    public String toIndex(ModelMap model,Principal user,
    		HttpServletRequest request) {
		//String token=request.getParameter("Authorization");
		//System.out.println("token:"+token);
		model.addAttribute("newStaff", true);
		model.addAttribute("deleteStaff", true);
		model.addAttribute("editStaff", true);
		//model.addAttribute("user", user);
		return "/system/staff/index";
	}
	
	@RequestMapping(value= {"/new","/staff/new"})
	public String toNewStaff(ModelMap model) {
		return "/system/staff/new";
	}
	
	@RequestMapping(value= {"/edit","/staff/edit/{id}"})
	public String toEditStaff(@PathVariable("id") String id,ModelMap model) {
		model.addAttribute("orderId", id);
		return "/system/staff/edit";
	}
	
	@RequestMapping(value= {"/show","/staff/show/{id}"})
	public String toShowStaff(@PathVariable("id") String id,ModelMap model) {
		model.addAttribute("orderId", id);
		return "/system/staff/show";
	}
}
