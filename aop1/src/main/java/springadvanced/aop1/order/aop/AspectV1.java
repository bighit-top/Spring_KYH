package springadvanced.aop1.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

    //springadvanced.aop1.order 패키지와 하위 패키지
    @Around("execution(* springadvanced.aop1.order..*(..))") //pointcut
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { //advice
        log.info("[log] {}", joinPoint.getSignature()); //join point signature
        return joinPoint.proceed();
    }

}
