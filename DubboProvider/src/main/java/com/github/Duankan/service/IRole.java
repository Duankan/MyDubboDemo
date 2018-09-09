package com.github.Duankan.service;

import com.github.Duankan.po.RolePo;

import java.util.List;

/**
 * shrio权限查询role接口
 */
public interface IRole {
    List<RolePo> getRoleByUserid(Integer Userid);
}
