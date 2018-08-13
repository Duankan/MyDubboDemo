package com.github.Duankan.service.impl;

import com.github.Duankan.dao.UserPoMapper;
import com.github.Duankan.po.UserPo;
import com.github.Duankan.service.IDubboService;
import com.github.Duankan.utils.RedisUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;


public class DubboServiceImpl   implements IDubboService {
    @Resource
    UserPoMapper userPoMapper;
//    ClassPathXmlApplicationContext context=BaseService.getContext();
//    RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
    @Override
    public String say(String name) {
        System.out.println("hello,this is dubbo "+name);
        return name;
    }

    @Override
    public UserPo queryById(Integer id) {
        return userPoMapper.queryById(id);
    }
}
