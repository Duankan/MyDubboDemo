package com.github.Duankan.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LamadaTest {
    @Test
    public void test() {
        List<Map<String, Object>> ls = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",1);
        map.put("msg","成功");
        ls.add(map);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("code",2);
        map2.put("msg","成功");
        ls.add(map2);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("code",3);
        map3.put("msg","成功");
        ls.add(map3);
        long a=ls.stream().filter(p->p!=null).count();
        System.out.println(a);
    }
}
