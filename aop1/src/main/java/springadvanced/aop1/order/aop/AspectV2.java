package springadvanced.aop1.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    //springadvanced.aop1.order 패키지와 하위 패키지
    @Pointcut("execution(* springadvanced.aop1.order..*(..))")
    private void allOrder() { //pointcut signature: 여러군데서 메소드처럼 사용 가능
    }

    @Around("allOrder()") //pointcut
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { //advice
        log.info("[log] {}", joinPoint.getSignature()); //join point signature
        return joinPoint.proceed();
    }

}
