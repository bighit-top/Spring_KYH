package springadvanced.aop1.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    //spring aspect 문제점 대안2: 지연 조회
//    private final ApplicationContext applicationContext; //너무 거대한 기능
    private final ObjectProvider<CallServiceV2> callServiceV2Provider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceV2Provider) {
        this.callServiceV2Provider = callServiceV2Provider;
    }

    public void external() {
        log.info("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceV2Provider.getObject();
        callServiceV2.internal(); //외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
