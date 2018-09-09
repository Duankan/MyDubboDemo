package com.github.Duankan.dao;

import com.github.Duankan.po.UsersPo;

public interface UsersPoMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(UsersPo record);
    int insertSelective(UsersPo record);
    UsersPo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(UsersPo record);
    int updateByPrimaryKey(UsersPo record);
    //根据用户名查询users
    UsersPo getUserByUsername(String username);
}