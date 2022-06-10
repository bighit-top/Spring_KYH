package springadvanced.aop1.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        internal(); //내부 메서드 호출: internal은 같은 인스턴스 내부에서 호출하는 것이기 때문에 internal()에는 기본적으로 aop가 적용되지 않는다.
    }

    public void internal() {
        log.info("call internal");
    }
}
