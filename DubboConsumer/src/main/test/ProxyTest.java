import com.github.Duan.Consumer.proxy.*;
import org.junit.Test;

public class ProxyTest {
    /**
     * 动态代理(jdk)
     */
    @Test
    public void test() {
        ProxyImpl proxy = new ProxyImpl();
        ProxyDemoInterface demoInterface= (ProxyDemoInterface) new DynamicProxyHandler(proxy).getProxyInstance();
        demoInterface.dosth();
    }
    /**
     * cglib代理
     */
    @Test
    public void test2(){
        CglibImpl target=new CglibImpl();
        CglibImpl proxy= (CglibImpl) new CglibProxyHandler(target).getProxyInstance();
        proxy.saySth();
    }
}
