package springadvanced.advanced1.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springadvanced.advanced1.trace.TraceId;
import springadvanced.advanced1.trace.TraceStatus;
import springadvanced.advanced1.trace.hellotrace.HelloTraceV2;
import springadvanced.advanced1.trace.logtrace.FieldLogTrace;
import springadvanced.advanced1.trace.logtrace.LogTrace;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepositoryV1.save()");

            //저장
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 다시 보냄: 정상 흐름을 방해하면 안됨
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
