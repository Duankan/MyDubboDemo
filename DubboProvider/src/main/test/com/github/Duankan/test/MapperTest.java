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
//    @Resource
//    TaskMapper taskMapper;

    @Test
    public void test() {
        System.out.println(userPoMapper);
        System.out.println(permissionPoMapper);
        List<PermissionPo> pos = permissionPoMapper.getPermissionByRoleid(1);
        System.out.println(pos.size());
        UserPo userPo = userPoMapper.queryById(9);
//        System.out.println(userPo.getName());
    }

    @Test
    public void test2() {
//        Task task = taskMapper.selectByPrimaryKey("11");
//        task.setTaskid("111");
//        task.setTaskname("测试");
//        taskMapper.insert(task);
    }
}
