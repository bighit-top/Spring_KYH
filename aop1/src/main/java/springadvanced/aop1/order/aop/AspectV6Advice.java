package springadvanced.aop1.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Around: ProceedingJoinPoint 를 사용해야 다음 advisor or target 을 호출할 수 있다.
 * 기타 다른 종류는 JoinPoint 사용 가능하다.
 */



@Slf4j
@Aspect
public class AspectV6Advice {

/*
    //springadvanced.aop1.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service 인 경우
    @Around("springadvanced.aop1.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            //@Before
            log.info("[트랜젝션 시작 {}]", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜젝션 커밋 {}]", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜젝션 롤백 {}]", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[리소스 릴리즈 {}]", joinPoint.getSignature());
        }
    }
*/

    @Before("springadvanced.aop1.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "springadvanced.aop1.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) { //Object result 이름으로 return 값이 매칭되어 파라미터로 사용됨
        log.info("[return] {} return={}", joinPoint.getSignature(), result); //return 결과를 바꿀수 없음
    }

//    @AfterReturning(value = "springadvanced.aop1.order.aop.Pointcuts.allOrder()", returning = "result")
//    public void doReturn2(JoinPoint joinPoint, String result) { //타겟?메서드의 반환 타입에 따라 반환됨
//        log.info("[return2] {} return={}", joinPoint.getSignature(), result); //return 결과를 바꿀수 없음
//    }

    @AfterThrowing(value = "springadvanced.aop1.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", ex);
    }

    @After("springadvanced.aop1.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
