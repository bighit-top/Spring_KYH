package springadvanced.advanced1.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepositoryVO;

    public void orderItem(String itemId) {
        orderRepositoryVO.save(itemId);
    }
}
