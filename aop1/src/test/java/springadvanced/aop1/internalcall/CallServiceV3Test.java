package springadvanced.aop1.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springadvanced.aop1.internalcall.aop.CallLogAspect;

@Slf4j
@Import({CallLogAspect.class})
@SpringBootTest
class CallServiceV3Test {

    @Autowired CallServiceV3 callServiceV3;

    @Test
    void external() {
        callServiceV3.external();
    }
}