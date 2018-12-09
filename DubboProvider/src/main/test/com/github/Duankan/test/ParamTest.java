package com.github.Duankan.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import java.util.Iterator;
import java.util.Set;
public class ParamTest extends BaseTest {
    @Test
    public void test(){
        String param= "{\n" +
                " \"url\":\"http://192.168.1.63:8080/hgis/ows\",\n" +
                " \"typename\":\"ktw:dileituban\",\n" +
                " \"groupFields\":\"行政区\",\n" +
                " \"statisticsFields\":[{\"operate\":\"sum\",\"field\":\"TBMJ\"}],\n" +
                " \"clip\":\"0\",\n" +
                " \"cql\":[\"01\", \"02\", \"03\", \"04\", \"10\", \"11\", \"12\", \"20\"]\n" +
                "}";
        JSONObject object=JSONObject.parseObject(param);
        String s= (String) ((JSONArray)object.get("cql")).get(0);
        object.remove("url");
        String jsonString="statistics="+object.toJSONString();
        System.out.println(object);
    }
    @Test
    public void test2(){
        String param=
                "{\n" +
                        "    \"url\": \"http://192.168.1.93:8080/hgis/ows\",\n" +
                        "    \"typename\": \"ktw:DLTB_hebing_chulihou\",\n" +
                        "    \"groupFields\": \"行政区\",\n" +
                        "    \"statisticsFields\": [\n" +
                        "      {\n" +
                        "        \"operate\": \"sum\",\n" +
                        "        \"field\": \"TBMJ\"\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"clip\": \"0\",\n" +
                        "    \"cqlCondition\": {\n" +
                        "      \"01\":\"耕地\",\"02\":\"园地\",\"03\":\"林地\",\"04\":\"草地\",\"10\":\"交通设施\",\"11\":\"水利用地\",\"12\":\"其他土地\",\"20\":\"城镇工矿\"\n" +
                        "    },\n" +
                        "    \"cqlField\": \"DLBM\"\n" +
                        "  }";
        JSONObject object=JSONObject.parseObject(param);
        JSONObject object1= (JSONObject) object.get("cqlCondition");
        Set<String> sets=object1.keySet();
        Iterator<String> it=sets.iterator();
        while (it.hasNext()){
            System.out.println(object1.getString(it.next()));
        }
        System.out.println(sets);
    }
}
