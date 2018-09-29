import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDemo {
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-demo-consumer.xml");
        context.start();

    }
    @Test
    public void test2(){
        //这是ktw的Git提交测试！！！1235855522
       Map map =new HashMap<String,DefaultSqlSession.StrictMap>();
       map.put("a","xx");
       map.put("b","yy");
        List<String> list = new ArrayList(map.values());
        for(String ls:list){
            System.out.println(ls);
        }

    }

}
