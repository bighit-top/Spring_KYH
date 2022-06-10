package springadvanced.aop1.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springadvanced.aop1.member.MemberService;
import springadvanced.aop1.member.annotation.ClassAop;
import springadvanced.aop1.member.annotation.MethodAop;

@Slf4j
@Import({ParameterTest.ParameterAspect.class})
@SpringBootTest
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {

        @Pointcut("execution(* springadvanced.aop1.member..*.*(..))") //"helloA" 를 가져다 씀
        private void allMember() {
        }

        //args
        //방법1
        @Around("allMember()")
        public Object logArg1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(), arg1);
            return joinPoint.proceed();
        }

        //방법2
        @Around("allMember() && args(arg, ..)") //파라미터 이름이 같아야 한다.
        public Object logArg2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2]{}, arg={}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        //방법3
        @Before("allMember() && args(arg, ..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] arg={}", arg);
        }

        //인스턴스(오브젝트)
        //this: 인스턴스 -> 프록시
        @Before("allMember() && this(obj)")
        public void thisArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[this]{}, obj={}", joinPoint.getSignature(), obj.getClass());
        }

        //target: 인스턴스 -> 실제 객체
        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[target]{}, obj={}", joinPoint.getSignature(), obj.getClass());
        }

        //annotation
        //target
        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target]{}, annotation={}", joinPoint.getSignature(), annotation);
        }

        //within
        @Before("allMember() && @within(within)")
        public void atWithin(JoinPoint joinPoint, ClassAop within) {
            log.info("[@within]{}, within={}", joinPoint.getSignature(), within);
        }

        //annotation
        @Before("allMember() && @annotation(annotation)")
        public void atWithin(JoinPoint joinPoint, MethodAop annotation) {
            log.info("[@annotation]{}, annotation.value={}", joinPoint.getSignature(), annotation.value()); //annotation에 있는 값을 가져올 수 있다
        }
    }

}
