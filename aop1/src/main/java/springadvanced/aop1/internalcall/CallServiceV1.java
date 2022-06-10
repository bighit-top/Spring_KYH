package springadvanced.aop1.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    //spring aspect 문제점 대안1: 자기 자신을 주입한다.
    // setter 주입: 컴포넌트 스캔 후 진행되므로 가능함
    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); //외부 메서드 호출: 자기 자신을 외부 주입을 통해 호출한다.
    }

    public void internal() {
        log.info("call internal");
    }
}
