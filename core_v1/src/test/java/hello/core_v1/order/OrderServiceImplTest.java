package hello.core_v1.order;

import hello.core_v1.discount.FixDiscountPolicy;
import hello.core_v1.member.Grade;
import hello.core_v1.member.Member;
import hello.core_v1.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        //의존관계 주입에서 생성자 주입으로 하면 코드 작성시부터 오류를 잡을 수 있다. 컴파일시 바로 오류난다.
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L, "A", 10000);

        //생성자 주입은 순수한 자바 코드에서 테스트 하기도 용이하다.
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "A", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "A", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}