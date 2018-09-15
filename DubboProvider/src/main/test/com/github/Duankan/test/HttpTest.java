package com.github.Duankan.test;

import com.github.Duankan.utils.HttpUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {
    /**
     * 测试我自己写的接口的302问题
     *
     * @throws Exception
     */
    @Test
    public void getTest() throws Exception {
        HttpUtils httpUtils = new HttpUtils();
//        httpUtils.doGet("https://blog.csdn.net/qq9808/article/details/78320816");
        Map<String, String> params = new HashMap<>();
        params.put("username", "java");
        params.put("password", "123");
        String response = httpUtils.doPost("http://localhost:8085/dubboConsumer/login", params,null);
//        String rps2=httpUtils.doGet("http://localhost:8085/dubboConsumer/redirect");
    }

    /**
     * 测试需要登录的cookie才能访问其他操作
     */
    @Test
    public void test2() {
        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> params = new HashMap<>();
        params.put("username", "dankin");
        params.put("password", "duan542300889e");
        Map<String,String> headers=new HashMap<>();
//        User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36
        headers.put("Accept","ext/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Accept-Language","zh-CN,zh;q=0.9");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        /**
         * 需要登录的cookie才能访问其他操作的解决方法：
         * 1.使用全局httpClient
         * 2.设置cookie（我还没有弄好！！！）
         */
        String resp = httpUtils.doPost("http://www.datalearner.com/login", params,headers);
        String resp2 = httpUtils.doGet("http://www.datalearner.com/manage/paper_note");
//        String rsp=httpUtils.doGet("http://localhost:8085/dubboConsumer/rest_getUser");
    }

    /**
     * 测试前后端分离，只返回数据的实验
     */
    @Test
    public void test3(){

    }
}
