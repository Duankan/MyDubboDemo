import com.github.Duan.Consumer.proxy.DynamicProxyHandler;
import com.github.Duan.Consumer.proxy.ProxyDemoInterface;
import com.github.Duan.Consumer.proxy.ProxyImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class ProxyTest {
    /**
     * 动态代理
     */
    @Test
    public void test(){
        ProxyImpl proxy=new ProxyImpl();
        ProxyDemoInterface demoInterface= (ProxyDemoInterface) Proxy.newProxyInstance(ProxyDemoInterface.class.getClassLoader(),
                new Class[]{ProxyDemoInterface.class},new DynamicProxyHandler(proxy));
        proxy.dosth();
    }
}
