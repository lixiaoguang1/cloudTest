package com.tiger.system;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	   
	   //配置mybatis的分页插件pageHelper
	   @Bean
	   public PageHelper pageHelper(){
	       PageHelper pageHelper = new PageHelper();
	       Properties properties = new Properties();
	       properties.setProperty("offsetAsPageNum","true");
	       properties.setProperty("rowBoundsWithCount","true");
	       properties.setProperty("reasonable","true");
	       properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
	       pageHelper.setProperties(properties);
	       return pageHelper;
	   }
}
