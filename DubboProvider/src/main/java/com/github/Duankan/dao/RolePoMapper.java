package com.github.Duankan.dao;

import com.github.Duankan.po.RolePo;

import java.util.List;

public interface RolePoMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(RolePo record);
    int insertSelective(RolePo record);
    RolePo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(RolePo record);
    int updateByPrimaryKey(RolePo record);
    //根据用户Id获取用户角色
    List<RolePo> getRoleByUserid(Integer userid);
}