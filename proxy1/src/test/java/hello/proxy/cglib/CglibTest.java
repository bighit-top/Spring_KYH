package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    //인터페이스 없은 구현체 클래스
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); //cglib 생성
        enhancer.setSuperclass(ConcreteService.class); //상속 대상 부모 지정
        enhancer.setCallback(new TimeMethodInterceptor(target)); //프록시에 적용할 실행 로직 지정
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass= {}", target.getClass());
        log.info("proxyClass= {}", proxy.getClass());

        proxy.call();
    }
}
