package com.tiger.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.system.common.Constant;
import com.tiger.system.dao.RoleMapper;
import com.tiger.system.entity.Role;
import com.tiger.system.model.ResResult;

@Controller
@RequestMapping(value="/system/v1/role")
public class RoleController {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RoleMapper roleMapper;
	
	 /**
	  * 添加角色信息
	  * @param role
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/addRole",method=RequestMethod.POST)
     public ResResult addRole(@RequestBody Role role) {
		 ResResult result=new ResResult();
		 logger.info("添加角色信息 begin");
		 try {
			 roleMapper.insert(role);
			 result.setMessage("添加角色信息成功");
			 result.setResCode(Constant.default_success_code);
		 }catch(Exception ex) {
			 logger.error("添加角色信息出错");
			 result.setMessage("添加角色信息失败");
			 result.setResCode(Constant.default_fail_code);
		 }
		 
		 logger.info("添加角色信息 end");
    	 return result;
     }
	 
	 /**
	  * 修改角色信息
	  * @param role
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/updateRole",method=RequestMethod.PUT)
	 public ResResult updateRole(@RequestBody Role role) {
		 ResResult result=new ResResult();
		 logger.info("修改角色信息 begin");
		 try {
			 roleMapper.updateByPrimaryKeySelective(role);
			 result.setMessage("修改角色信息成功");
			 result.setResCode(Constant.default_success_code);
		 }catch(Exception ex) {
			 logger.error("修改角色信息出错");
			 result.setMessage("修改角色信息失败");
			 result.setResCode(Constant.default_fail_code);
		 }
		 
		 logger.info("修改角色信息 end");
    	 return result;
	 }
	 
	 /**
	  * 删除角色信息
	  * @param roleId
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/deleteRole",method=RequestMethod.DELETE)
	 public ResResult delRole(@RequestParam("roleId") String roleId) {
		 ResResult result=new ResResult();
		 logger.info("删除角色信息 begin");
		 try {
			 roleMapper.deleteByPrimaryKey(roleId);
			 result.setMessage("删除角色信息成功");
			 result.setResCode(Constant.default_success_code);
		 }catch(Exception ex) {
			 logger.error("删除角色信息出错");
			 result.setMessage("删除角色信息失败");
			 result.setResCode(Constant.default_fail_code);
		 }
		 
		 logger.info("删除角色信息 end");
    	 return result;
	 }
	 
	 /**
	  * 获取角色信息
	  * @param roleId
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/searchRole",method=RequestMethod.GET)
	 public ResResult searchRole(@RequestParam("roleId") String roleId) {
		 ResResult result=new ResResult();
		 logger.info("获取角色信息 begin");
		 try {
			 Role role=roleMapper.selectByPrimaryKey(roleId);
			 result.setMessage("获取角色信息成功");
			 result.setResCode(Constant.default_success_code);
			 result.setData(role);
		 }catch(Exception ex) {
			 logger.error("获取角色信息出错");
			 result.setMessage("获取角色信息失败");
			 result.setResCode(Constant.default_fail_code);
		 }
		 
		 logger.info("获取角色信息 end");
    	 return result;
	 }
	 
	 /**
	  * 查询角色信息
	  * @param roleId
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/searchRoles/{index}/{limit}",method=RequestMethod.GET)
	 public ResResult searchRoles(@PathVariable("index") int index,
			 @PathVariable("limit") int limit,
			 @RequestParam("keyword") String keyword) {
		 ResResult result=new ResResult();
		 logger.info("查询角色信息 begin");
		 try {
			 if(index==-1 || limit==-1) {
				 List<Role> list=roleMapper.selectRoles(keyword);
				 result.setData(list);
			 }else {
				 PageHelper.startPage(index, limit);
				 PageInfo<Role> pages=new PageInfo<>(roleMapper.selectRoles(keyword));
				 result.setData(pages);
			 }
			 result.setMessage("查询角色信息成功");
			 result.setResCode(Constant.default_success_code);
		 }catch(Exception ex) {
			 ex.printStackTrace();
			 logger.error("查询角色信息出错:{}",ex.getMessage());
			 result.setMessage("查询角色信息失败");
			 result.setResCode(Constant.default_fail_code);
		 }
		 logger.info("查询角色信息 end");
    	 return result;
	 }
}
