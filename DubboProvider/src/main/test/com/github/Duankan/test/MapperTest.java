package com.github.Duankan.test;

import com.github.Duankan.dao.UserPoMapper;
import com.github.Duankan.po.UserPo;
import org.junit.Test;

import javax.annotation.Resource;

public class MapperTest extends BaseTest {

    @Resource
    UserPoMapper userPoMapper;

    @Test
    public void test(){
        System.out.println(userPoMapper);
        UserPo userPo=userPoMapper.queryById(9);
        System.out.println(userPo.getName());
    }
}
