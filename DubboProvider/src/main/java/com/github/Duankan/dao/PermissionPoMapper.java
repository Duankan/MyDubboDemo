package com.github.Duankan.dao;

import com.github.Duankan.po.PermissionPo;

import java.util.List;

public interface PermissionPoMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(PermissionPo record);
    int insertSelective(PermissionPo record);
    PermissionPo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(PermissionPo record);
    int updateByPrimaryKey(PermissionPo record);
    //根据usrid查询用户权限
    List<PermissionPo> getPermissionByRoleid(Integer roleid);
}