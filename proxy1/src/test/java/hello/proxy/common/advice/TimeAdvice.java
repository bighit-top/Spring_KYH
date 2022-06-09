package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        log.info("TimeProxy 생성");
        long startTime = System.currentTimeMillis();

        //로직
        Object result = invocation.proceed(); //타겟을 찾아 실제를 호출해줌

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime= {}", resultTime);
        return result;
    }
}
