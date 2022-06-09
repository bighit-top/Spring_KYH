package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)  throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

//        Object result = method.invoke(target, args);
        Object result = methodProxy.invoke(target, args); //실제 호출: 인스턴스 메서드, 인수

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTIme={}", resultTime);
        return result;
    }
}
