package com.github.Duankan.service.impl;

import com.github.Duankan.dao.PermissionPoMapper;
import com.github.Duankan.po.PermissionPo;
import com.github.Duankan.service.IPermission;
import javax.annotation.Resource;
import java.util.List;

public class PermissionServiceImpl implements IPermission {
    @Resource
    PermissionPoMapper permissionPoMapper;
    @Override
    public List<PermissionPo> getPermissionByRoleid(Integer roleid) {
        return permissionPoMapper.getPermissionByRoleid(roleid);
    }
}
