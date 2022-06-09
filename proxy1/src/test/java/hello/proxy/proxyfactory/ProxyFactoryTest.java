package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl(); //실제 객체
        ProxyFactory proxyFactory = new ProxyFactory(target); //proxy에 실제 객체를 알려줌
        proxyFactory.addAdvice(new TimeAdvice()); //advice 지정
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass= {}", target.getClass());
        log.info("proxyClass= {}", proxy.getClass());

        proxy.save();

        //ProxyFactory에서 제공하는 테스트
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    void concreteProxy() {
        ConcreteService target = new ConcreteService(); //실제 객체
        ProxyFactory proxyFactory = new ProxyFactory(target); //proxy에 실제 객체를 알려줌
        proxyFactory.addAdvice(new TimeAdvice()); //advice 지정
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass= {}", target.getClass());
        log.info("proxyClass= {}", proxy.getClass());

        proxy.call();

        //ProxyFactory에서 제공하는 테스트
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    void proxyTargetClass() {
        ServiceInterface target = new ServiceImpl(); //실제 객체
        ProxyFactory proxyFactory = new ProxyFactory(target); //proxy에 실제 객체를 알려줌
        proxyFactory.setProxyTargetClass(true); //CGLIB 사용 지정
        proxyFactory.addAdvice(new TimeAdvice()); //advice 지정
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass= {}", target.getClass());
        log.info("proxyClass= {}", proxy.getClass());

        proxy.save();

        //ProxyFactory에서 제공하는 테스트
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

}
