package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //프록시 생성: 인수=클래스 생성 위치, 인터페이스, 로직
        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        proxy.call(); //target.call()
        log.info("targetClass= {}", target.getClass());
        log.info("proxyClass= {}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //프록시 생성(인터페이스 기반): 인수=클래스 생성 위치, 인터페이스, 로직
        BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call(); //target.call()
        log.info("targetClass= {}", target.getClass());
        log.info("proxyClass= {}", proxy.getClass());
    }

}
