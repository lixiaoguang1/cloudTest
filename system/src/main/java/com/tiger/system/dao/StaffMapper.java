package com.tiger.system.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiger.system.entity.Staff;

@Mapper
public interface StaffMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
    
    List<Staff> selects(String keyword);
    
    int insertStarfRoles(@Param("staff") String staff,
    		@Param("list") List<String> list) throws SQLException;
}