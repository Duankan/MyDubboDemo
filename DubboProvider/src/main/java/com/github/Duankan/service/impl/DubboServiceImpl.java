package com.github.Duankan.service.impl;

import com.github.Duankan.service.IDubboService;

public class DubboServiceImpl implements IDubboService {
    @Override
    public String say(String name) {
        System.out.println("hello,this is dubbo "+name);
        return name;
    }
}
