package com.tiger.apigateway.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tiger.apigateway.entity.Staff;
@Mapper
public interface StaffMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String orderId);
    
    Staff selectByStaffNo(String staffNo);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
}