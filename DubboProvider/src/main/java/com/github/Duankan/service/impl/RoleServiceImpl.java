package com.github.Duankan.service.impl;

import com.github.Duankan.dao.RolePoMapper;
import com.github.Duankan.po.RolePo;
import com.github.Duankan.service.IRole;
import javax.annotation.Resource;
import java.util.List;

public class RoleServiceImpl implements IRole {
    @Resource
    RolePoMapper rolePoMapper;
    @Override
    public List<RolePo> getRoleByUserid(Integer Userid) {
        return rolePoMapper.getRoleByUserid(Userid);
    }
}
