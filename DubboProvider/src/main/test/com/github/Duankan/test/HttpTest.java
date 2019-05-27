package com.github.Duankan.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.Duankan.utils.HttpRequestUtil;
import com.github.Duankan.utils.HttpUtils;
import com.github.Duankan.utils.ZipUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        Map<String, String> params = new HashMap<>();
        params.put("username", "java");
        params.put("password", "123");
        String response = httpUtils.doPost("http://localhost:8085/dubboConsumer/login", params, null);
        String rps2 = httpUtils.doPost("http://localhost:8085/dubboConsumer/rest_getUser", null, null);
        //com.alibaba.fastjson解析json格式的数据
        JSONObject object = (JSONObject) JSONObject.parse(rps2);
        String code = object.getString("code");
        String msg = object.getString("msg");
        JSONObject entity = object.getJSONObject("object");
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
        Map<String, String> headers = new HashMap<>();
//        User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36
        headers.put("Accept", "ext/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        /**
         * 需要登录的cookie才能访问其他操作的解决方法：
         * 1.使用全局httpClient
         * 2.设置cookie（我还没有弄好！！！）
         */
        String resp = httpUtils.doPost("http://www.datalearner.com/login", params, headers);
        String resp2 = httpUtils.doGet("http://www.datalearner.com/manage/paper_note");
        //jsoup解析网页
        Document document = Jsoup.parse(resp2, "UTF-8");
        Element element = document.getElementById("main_div");
        Elements elements = element.getElementsByClass("list-group");
        List<Map<String, String>> list = new ArrayList<>();
        for (Element el : elements) {
            Map<String, String> map = new HashMap<String, String>();
            String title = el.getElementsByClass("side-bar-title").first().text();
            map.put("side-bar-title", title);
            list.add(map);
        }
        System.out.println(list.size());
//        String rsp=httpUtils.doGet("http://localhost:8085/dubboConsumer/rest_getUser");
    }

    /**
     * 测试前后端分离，只返回数据的实验
     */
    @Test
    public void test3() {
        HttpUtils httpUtils = new HttpUtils();
        String url = "http://192.168.1.63:8080/hgis/ows?request=aggregate&service=wps";
        JSONObject jsonObject1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("field", "TBMJ");
        jsonObject2.put("operate", "sum");
        jsonArray.add(jsonObject2);
        jsonObject1.put("statisticsFields", jsonArray);
        jsonObject1.put("typename", "ktw:dileituban");
        jsonObject1.put("clip", "0");
        jsonObject1.put("groupFields", "行政区");
        Map<String, String> params = new HashMap<>();
        params.put("statistics", jsonObject1.toJSONString());
        Map<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/x-www-form-urlencoded");
        String re = httpUtils.doPost(url, params, head);
//        String res= HttpRequestUtil.sendPost(url,new BasicNameValuePair("statistics", jsonObject1.toJSONString()),"application/x-www-form-urlencoded");
        int a = 0;
    }

    @Test
    public void test4() {
        HttpUtils httpUtils = new HttpUtils();
        String res = httpUtils.doGet("http://192.168.1.90:8086/hgis/wfs?request=GetFeature&typeName=ktw:city1&outputFormat=application/json&count=10");
    }

    @Test
    public void test5() {
        String errorJson = "{}";
        String typeName = "{\n" +
                "    \"typeName\":[\n" +
                "      {\n" +
                "        \"cityLayer\":\"ktw:city1\",\n" +
                "        \"countyLayer\":\"ktw:county1\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"test\":\"1111\"\n" +
                "  }";
        try {
            JSONObject object = (JSONObject) JSONObject.parse(typeName);
            JSONArray array = (JSONArray) object.get("typeName");
            String cityLayer = ((JSONObject) array.get(0)).getString("cityLayer");
            String countyLayer = ((JSONObject) array.get(0)).getString("countyLayer");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图层传递错误！");
        } finally {
            System.out.println(1111);
        }
    }

    @Test
    public void ZipTest() {
        /** 测试压缩方法1 */
        try {
            FileOutputStream fos1 = new FileOutputStream(new File("C:\\Users\\ljiu\\Desktop\\mytest01.zip"));
            ZipUtil.toZip("C:\\Users\\ljiu\\Desktop\\qq2.png", fos1, true);
        } catch (Exception e) {

        }
    }
}
