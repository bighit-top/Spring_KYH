package springadvanced.advanced1.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springadvanced.advanced1.trace.TraceStatus;
import springadvanced.advanced1.trace.logtrace.LogTrace;
import springadvanced.advanced1.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepositoryVO;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                orderRepositoryVO.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem()");
    }
}
