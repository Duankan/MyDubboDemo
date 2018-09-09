package com.github.Duankan.service;

import com.github.Duankan.po.PermissionPo;

import java.util.List;

/**
 * shiro权限查询用户权限接口
 */
public interface IPermission {
    List<PermissionPo> getPermissionByRoleid(Integer roleid);
}
