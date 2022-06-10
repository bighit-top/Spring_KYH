package springadvanced.aop1.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    //springadvanced.aop1.order 패키지와 하위 패키지인 경우
    @Pointcut("execution(* springadvanced.aop1.order..*(..))")
    public void allOrder() {
    } //pointcut signature: 여러군데서 메소드처럼 사용 가능

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
