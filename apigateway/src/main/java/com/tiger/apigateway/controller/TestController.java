package com.tiger.apigateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiger.apigateway.security.JwtAuthenticationRequest;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping(value="/test",method=RequestMethod.POST)
	@ResponseBody
    public String test1(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		System.out.println(authenticationRequest.toString());
    	return "hello world";
    }
}
