package com.tiger.system.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.tiger.system.dao.StaffMapper;
import com.tiger.system.entity.Staff;
import com.tiger.system.model.ResResult;

@Controller
@RequestMapping(value="/system/v1")
public class StaffController {
	
	@Autowired
	private StaffMapper staffMapper;
	
	private final static Logger logger=
			LoggerFactory.getLogger(StaffController.class);
	
	/**
	 * 添加操作员信息
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="/staff/addStaff"
			,method=RequestMethod.POST)
	@ResponseBody
	public  ResResult addStaff(@RequestBody Staff staff) {
		ResResult result=new ResResult();
		try{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        final String rawPassword = staff.getStaffPwd();
	        staff.setStaffPwd(encoder.encode(rawPassword));
			staffMapper.insert(staff);
			result.setMessage("添加操作员信息成功");
			result.setResCode(Constant.default_success_code);
		}catch(Exception ex) {
			logger.error("添加操作员信息失败:{}",ex.getMessage());
			result.setMessage("添加操作员信息成功");
			result.setResCode(Constant.default_fail_code);
		}
		return result;
	}
	
	/**
	 * 更新操作员信息
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="/staff/updateStaff"
			,method=RequestMethod.PUT)
	@ResponseBody
	public ResResult updateStaff(@RequestBody Staff staff) {
		ResResult result=new ResResult();
		try{
			if(null !=staff && null !=staff.getStaffPwd() && !"".equals(staff.getStaffPwd()))
			{
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		        final String rawPassword = staff.getStaffPwd();
		        staff.setStaffPwd(encoder.encode(rawPassword));
			}
			staffMapper.updateByPrimaryKeySelective(staff);
			result.setMessage("修改操作员信息成功");
			result.setResCode(Constant.default_success_code);
		}catch(Exception ex) {
			logger.error("修改操作员信息失败:{}",ex.getMessage());
			result.setMessage("修改操作员信息成功");
			result.setResCode(Constant.default_fail_code);
		}
		return result;
	}
	
	/**
	 * 删除操作员信息
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="/staff/delStaff"
			,method=RequestMethod.DELETE)
	@ResponseBody
	public ResResult delStaff(@RequestParam("staffId") String staffId) {
		ResResult result=new ResResult();
		try {
			staffMapper.deleteByPrimaryKey(staffId);
			result.setMessage("删除操作员信息成功！");
			result.setResCode(Constant.default_success_code);
		}catch (Exception e) {
			logger.error("删除操作员信息失败：{}",e.getMessage());
			result.setMessage("删除操作员信息失败！");
			result.setResCode(Constant.default_fail_code);
		}
		return result;
	}
	
    /**
     * 获取操作员信息
     * @param staffId
     * @return
     */
	@RequestMapping(value="/staff/searchStaff"
			,method=RequestMethod.GET)
	@ResponseBody
	public ResResult serchStaff(@RequestParam("staffId") String staffId) {
		ResResult result=new ResResult();
		logger.info("查询操作员信息begin");
		try {
			Staff staff=staffMapper.selectByPrimaryKey(staffId);
			result.setMessage("获取操作员信息成功");
			result.setResCode(Constant.default_success_code);
			result.setData(staff);
		}catch(Exception ex) {
			logger.error("获取操作员信息失败:{}",ex.getMessage());
			result.setMessage("获取操作员信息失败");
			result.setResCode(Constant.default_fail_code);
		}
		logger.info("查询操作员信息end");
		return result;
	}
	
	/**
	 *查询操作员信息
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value="/staff/searchStaffs/{index}/{limit}"
			,method=RequestMethod.GET)
	@ResponseBody
	public ResResult searchStaffs(@PathVariable("index") int index,
			@PathVariable("limit")int limit,@RequestParam("keyword") String keyword) {
		ResResult result=new ResResult();
		logger.info("查询操作员信息begin");
		try {
            if(index==-1 || limit ==-1) {
            	List<Staff> staffs=staffMapper.selects(keyword);
            	result.setData(staffs);
			}else {
				PageHelper.startPage(index, limit);
				PageInfo<Staff> pages=new PageInfo<>(staffMapper.selects(keyword));
				result.setData(pages);
			}
			result.setMessage("查询操作员信息成功");
			result.setResCode(Constant.default_success_code);
		}catch(Exception ex) {
			logger.error("查询操作员信息失败:{}",ex.getMessage());
			result.setMessage("查询操作员信息失败");
			result.setResCode(Constant.default_fail_code);
		}
		logger.info("查询操作员信息end");
		return result;
	}

}
