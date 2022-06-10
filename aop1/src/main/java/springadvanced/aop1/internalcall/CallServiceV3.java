package springadvanced.aop1.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalService internalService;

    //spring aspect 문제점 대안3: 구조 변경(분리)
    public void external() {
        log.info("call external");
        internalService.internal(); //외부 메서드 호출
    }
}
