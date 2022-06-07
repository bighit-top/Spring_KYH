package springadvanced.advanced1.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springadvanced.advanced1.trace.TraceStatus;
import springadvanced.advanced1.trace.hellotrace.HelloTraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

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
