package com.github.Duan.Consumer.controller;

import com.github.Duankan.base.ResponseEnum;
import com.github.Duankan.base.ResponsePojo;
import com.github.Duankan.po.UserPo;
import com.github.Duankan.service.IDubboService;
import com.github.Duankan.service.IPermission;
import com.github.Duankan.service.IRole;
import com.github.Duankan.service.IUsers;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dubboConsumer")
public class Dubbo_consumer {

    @Autowired
    IDubboService dubboService;
    @Autowired
    IUsers iUsers;
    @Autowired
    IRole iRole;
    @Autowired
    IPermission iPermission;
    @RequestMapping("/consumer")
    public String test(){
        iUsers.getUserByUsername("java");
        iRole.getRoleByUserid(1);
        iPermission.getPermissionByRoleid(1);
        UserPo userPo = dubboService.queryById(9);
        if(userPo!=null){
            System.out.println("用户名字："+userPo.getName());
        }
        return "consumer";
    }
    //写一个重定向测试httpClient
    @RequestMapping("/redirect")
    public String redirect(){
        return "redirect:/dubboConsumer/consumer";
    }
    //shiro
    @RequiresRoles("admin1")
    @RequestMapping("/shiro")
    public String testRole(){
        try{

        }
        catch (UnauthorizedException e){
            throw e;
        }
        return null;
    }
    @RequestMapping("/login")
    public String login(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            //测试httpClient 302重定向
//            return "redirect:/consumer";
            return "success";
        }
        catch (IncorrectCredentialsException e){
            System.out.println("fail!密码错误！");
            throw e;
        }
        catch (AuthenticationException e){
            throw e;
        }
    }
    //前后端分离，rest风格
    @RequiresPermissions("/dubboConsumer/rest_getUser")
    @RequestMapping("/rest_getUser")
    @ResponseBody
    public ResponsePojo<UserPo> getUserRest(){
        ResponsePojo<UserPo> responsePojo=new ResponsePojo<>();
        UserPo po=dubboService.queryById(9);
        if (po!=null){
            responsePojo.setCode(ResponseEnum.SUCCESS.getCode());
            responsePojo.setMsg(ResponseEnum.SUCCESS.getDisplayName());
            responsePojo.setObject(po);
            return responsePojo;
        }
        else {
            responsePojo.setCode(ResponseEnum.FAIL.getCode());
            responsePojo.setMsg(ResponseEnum.FAIL.getDisplayName());
            return responsePojo;
        }
    }
}
