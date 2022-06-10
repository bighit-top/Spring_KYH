package springadvanced.aop1.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springadvanced.aop1.member.MemberService;
import springadvanced.aop1.member.MemberServiceImpl;
import springadvanced.aop1.proxyvs.code.ProxyDIAspect;

//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB 프록시
@SpringBootTest
@Slf4j
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    //JDK 동적 프록시: DI 안됨 = 인터페이스 기반으로 만들어진 JDK 동적 프록시는 구체클래스를 모른다.
    //CGLIB 프록시: DI 됨 = 구체클래스를 기반으로 만들어진 CGLIB 프록시는 인터페이스(부모)를 안다.
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
