package com.github.Duankan.service;

import com.github.Duankan.po.UserPo;

public interface IDubboService {
    public String say(String name);
    public UserPo queryById(Integer id);
}
