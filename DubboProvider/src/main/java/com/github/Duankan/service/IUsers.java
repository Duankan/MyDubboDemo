package com.github.Duankan.service;

import com.github.Duankan.po.UsersPo;

/**
 * 查询shiro权限用户user实
 */
public interface IUsers {
    public UsersPo getUserByUsername(String Username);
}
