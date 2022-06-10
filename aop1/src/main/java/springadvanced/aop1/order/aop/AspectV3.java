package springadvanced.aop1.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    /**
     * pointcut
     */
    //springadvanced.aop1.order 패키지와 하위 패키지인 경우
    @Pointcut("execution(* springadvanced.aop1.order..*(..))")
    private void allOrder() {} //pointcut signature: 여러군데서 메소드처럼 사용 가능

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}


    /**
     * advisor = pointcut + advice
     */
    @Around("allOrder()") //pointcut
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { //advice
        log.info("[log] {}", joinPoint.getSignature()); //join point signature
        return joinPoint.proceed();
    }

    //springadvanced.aop1.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service 인 경우
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            log.info("[트랜젝션 시작 {}]", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜젝션 커밋 {}]", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜젝션 롤백 {}]", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈 {}]", joinPoint.getSignature());
        }
    }
}
