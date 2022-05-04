package hello.core_v1.order;

import hello.core_v1.AppConfig;
import hello.core_v1.discount.FixDiscountPolicy;
import hello.core_v1.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService; // = new MemberServiceImpl();
    OrderService orderService; // = new OrderServiceImpl();

    //DI
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(memberId, "과자", 10000);
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test //의존관계 필드 주입 테스트 : NullPointerException
    void fieldInjectionTest() {
/*
        OrderServiceImpl orderService = new OrderServiceImpl();
        //필드 주입을 하더라도 외부에서 접근하기 위해서는 어차피 setter가 필요함
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new FixDiscountPolicy());
        orderService.createOrder(1L, "A", 10000);
*/
    }

}
