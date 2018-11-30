package com.github.Duankan.service.impl;

import com.github.Duankan.dao.UsersPoMapper;
import com.github.Duankan.po.UsersPo;
import com.github.Duankan.service.IUsers;
import javax.annotation.Resource;

public class UsersServiceImpl implements IUsers {
    @Resource
    UsersPoMapper usersPoMapper;
    @Override
    public UsersPo getUserByUsername(String Username) {
        return usersPoMapper.getUserByUsername(Username);
    }
}
