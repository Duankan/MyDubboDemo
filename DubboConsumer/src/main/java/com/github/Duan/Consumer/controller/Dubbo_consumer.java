package com.github.Duan.Consumer.controller;

import com.github.Duankan.service.IDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dubboConsumer")
public class Dubbo_consumer {

    @Autowired
    IDubboService dubboService;
    @RequestMapping("/consumer")
    public String test(){
        return "consumer";
    }
}
