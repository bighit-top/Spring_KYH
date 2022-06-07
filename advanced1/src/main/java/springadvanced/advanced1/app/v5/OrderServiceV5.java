package springadvanced.advanced1.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springadvanced.advanced1.trace.callback.TraceCallback;
import springadvanced.advanced1.trace.callback.TraceTemplate;
import springadvanced.advanced1.trace.logtrace.LogTrace;
import springadvanced.advanced1.trace.template.AbstractTemplate;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepositoryVO;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(OrderRepositoryV5 orderRepositoryVO, LogTrace trace) {
        this.orderRepositoryVO = orderRepositoryVO;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        traceTemplate.execute("OrderService.orderItem()", () -> {
            orderRepositoryVO.save(itemId);
            return null;
        });
    }
}
