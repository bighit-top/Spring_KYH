package springadvanced.aop1.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springadvanced.aop1.member.MemberService;

/**
 *  application.properties: set proxy (default=true)
 *  spring.aop.proxy-target-class=true CGLIB
 *  spring.aop.proxy-target-class=false JDK 동적 프록시
 */
@Slf4j
@Import({ThisTargetTest.ThisTargetAspect.class})
@SpringBootTest//(properties = "spring.aop.proxy-target-class=false")
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("hello");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {

        //인터페이스 대상: 부모 타입 허용
        @Around("this(springadvanced.aop1.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(springadvanced.aop1.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //구체 대상
        @Around("this(springadvanced.aop1.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(springadvanced.aop1.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
