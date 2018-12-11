package com.tiger.apigateway.dao;

import com.tiger.apigateway.entity.Staff;

public interface StaffMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
}