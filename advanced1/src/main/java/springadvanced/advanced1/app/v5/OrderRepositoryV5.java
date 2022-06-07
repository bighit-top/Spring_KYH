package springadvanced.advanced1.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springadvanced.advanced1.trace.callback.TraceCallback;
import springadvanced.advanced1.trace.callback.TraceTemplate;
import springadvanced.advanced1.trace.logtrace.LogTrace;
import springadvanced.advanced1.trace.template.AbstractTemplate;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace trace) {
        this.traceTemplate = new TraceTemplate(trace);
    }
    public void save(String itemId) {

        traceTemplate.execute("OrderRepository.save()", () -> {
            //저장
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
