package com.tiger.apigateway.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tiger.apigateway.entity.Service;
@Mapper
public interface ServiceMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
    
    List<Service> getServices();
}