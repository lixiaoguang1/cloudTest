package com.tiger.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiger.system.entity.Role;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
     * 添加角色服务的关联关系
     * @return
     */
    int insertServiceRoleRelation(@Param("serviceNo") String serviceNo,@Param("list") List<String> roles);
    List<Role> selectRoles(@Param("keyword") String keyword);
    
    List<Role> selectAllRoles(@Param("list") List<Role> list);
}