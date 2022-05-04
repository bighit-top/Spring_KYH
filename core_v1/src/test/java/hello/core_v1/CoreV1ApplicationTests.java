package hello.core_v1;

import hello.core_v1.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreV1ApplicationTests {

	//의존관계 필드 주입. 테스트 코드에서는 사용해도 괜찮다.
	@Autowired OrderService orderService;

	@Test
	void contextLoads() {
	}

}
