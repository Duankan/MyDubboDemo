package com.github.Duankan.test;

import com.github.Duankan.dao.PermissionPoMapper;
import com.github.Duankan.dao.UserPoMapper;
import com.github.Duankan.po.PermissionPo;
import com.github.Duankan.po.UserPo;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class MapperTest extends BaseTest {

    @Resource
    UserPoMapper userPoMapper;
    @Resource
    PermissionPoMapper permissionPoMapper;

    @Test
    public void test(){
        System.out.println(userPoMapper);
        System.out.println(permissionPoMapper);
        List<PermissionPo> pos=permissionPoMapper.getPermissionByRoleid(1);
        System.out.println(pos.size());
        UserPo userPo=userPoMapper.queryById(9);
//        System.out.println(userPo.getName());
    }
}
