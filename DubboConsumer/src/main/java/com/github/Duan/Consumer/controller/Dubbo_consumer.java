package com.github.Duan.Consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.Duan.Consumer.async.AsyncTask;
import com.github.Duan.Consumer.biz.DownExcelBiz;
import com.github.Duankan.base.ResponseEnum;
import com.github.Duankan.base.ResponsePojo;
import com.github.Duankan.po.Task;
import com.github.Duankan.po.UserPo;
import com.github.Duankan.service.*;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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
    @Autowired
    AsyncTask asyncTask;
    @Autowired
    DownExcelBiz downExcelBiz;
    @Autowired
    ITask iTask;

    @RequestMapping("/consumer")
    public String test() {
        iUsers.getUserByUsername("java");
        iRole.getRoleByUserid(1);
        iPermission.getPermissionByRoleid(1);
        UserPo userPo = dubboService.queryById(9);
        if (userPo != null) {
            System.out.println("用户名字：" + userPo.getName());
        }
        return "consumer";
    }

    //写一个重定向测试httpClient
    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:/dubboConsumer/consumer";
    }

    //shiro
    @RequiresRoles("admin1")
    @RequestMapping("/shiro")
    public String testRole() {
        try {
        } catch (UnauthorizedException e) {
            throw e;
        }
        return null;
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            //测试httpClient 302重定向
//            return "redirect:/consumer";
            return "success";
        } catch (IncorrectCredentialsException e) {
            System.out.println("fail!密码错误！");
            throw e;
        } catch (AuthenticationException e) {
            throw e;
        }
    }

    //前后端分离，rest风格
    @RequiresPermissions("/dubboConsumer/rest_getUser")
    @RequestMapping("/rest_getUser")
    @ResponseBody
    public ResponsePojo<UserPo> getUserRest() {
        ResponsePojo<UserPo> responsePojo = new ResponsePojo<>();
        UserPo po = dubboService.queryById(9);
        if (po != null) {
            responsePojo.setCode(ResponseEnum.SUCCESS.getCode());
            responsePojo.setMsg(ResponseEnum.SUCCESS.getDisplayName());
            responsePojo.setObject(po);
            return responsePojo;
        } else {
            responsePojo.setCode(ResponseEnum.FAIL.getCode());
            responsePojo.setMsg(ResponseEnum.FAIL.getDisplayName());
            return responsePojo;
        }
    }

    //get服务
    @RequestMapping("/HttpGet")
    @ResponseBody
    public ResponsePojo<String> accessResoureForHttpGet(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();//判断请求的方法，是get,post,head
        ResponsePojo<String> responsePojo = new ResponsePojo<>();
        responsePojo.setCode(ResponseEnum.SUCCESS.getCode());
        responsePojo.setMsg(ResponseEnum.SUCCESS.getDisplayName());
        responsePojo.setObject("httget");
        try {
            System.out.println("开始睡眠三分钟!");
            Thread.sleep(1000 * 15);
            System.out.println("睡眠结束!");
        } catch (Exception e) {

        }
        return responsePojo;
    }

    /**
     * @param
     * @return
     * @desc 导出excel
     */
    @RequestMapping("/export")
    @ResponseBody
    public ResponsePojo<String> export() {
        ResponsePojo<String> responsePojo = new ResponsePojo<>();
        try {
            Thread.sleep(1000 * 15);
            responsePojo.setCode(ResponseEnum.SUCCESS.getCode());
            responsePojo.setMsg(ResponseEnum.SUCCESS.getDisplayName());
            responsePojo.setObject("C:\\Users\\ljiu\\Desktop\\Map.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responsePojo;
    }

    @RequestMapping("/async")
    @ResponseBody
    public ResponsePojo<String> Async() {
        ResponsePojo<String> responsePojo = new ResponsePojo<>();
        responsePojo.setCode(ResponseEnum.SUCCESS.getCode());
        responsePojo.setMsg(ResponseEnum.SUCCESS.getDisplayName());
        System.out.println(Thread.currentThread().getId());
        long startTime = System.currentTimeMillis();
        System.out.println("enter time:" + startTime);
        asyncTask.testAsync();
        long endtime = System.currentTimeMillis();
        System.out.println("leave time:" + endtime);
        System.out.println("时长：" + (startTime - endtime));
        responsePojo.setObject(String.valueOf(endtime));
        return responsePojo;
    }

    @RequestMapping("/startDownAsync")
    @ResponseBody
    public ResponsePojo<String> download(@RequestBody String param) {
        JSONObject object = (JSONObject) JSONObject.parse(param);
        downExcelBiz.startTask(object.getString("taskId"));
        //插入一条task到数据库
        Task task = new Task();
        task.setBegintime(new Date());
        task.setEndtime(null);
        task.setProgress("0%");
        task.setTaskcount("0");
        task.setTaskid(object.getString("taskId"));
        task.setTaskname(object.getString("taskName"));
        iTask.insertTask(task);

        ResponsePojo<String> responsePojo = new ResponsePojo<>();
        responsePojo.setCode(ResponseEnum.SUCCESS.getCode());
        responsePojo.setMsg(ResponseEnum.SUCCESS.getDisplayName());
        responsePojo.setObject(object.getString("taskId"));
        return responsePojo;
    }

}
