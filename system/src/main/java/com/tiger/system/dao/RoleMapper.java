package com.tiger.system.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tiger.system.entity.Role;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}