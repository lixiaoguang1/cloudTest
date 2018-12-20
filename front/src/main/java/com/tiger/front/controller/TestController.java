package com.tiger.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping("/hello")
	@ResponseBody
    public String hello() {
    	return "hello world";
    }
}
