package hello.core_v1.singleton;

import hello.core_v1.AppConfig;
import hello.core_v1.member.MemberRepository;
import hello.core_v1.member.MemberServiceImpl;
import hello.core_v1.order.OrderService;
import hello.core_v1.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //같은 인스턴스인지 확인 : 같다.
        System.out.println("memberRepository.memberRepository = " + memberRepository);
        System.out.println("memberService.memberRepository1 = " + memberRepository1);
        System.out.println("orderService.memberRepository2 = " + memberRepository2);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test //@Configuration 에 관하여
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        //AppConfig도 bean 등록됨 : hello.core_v1.AppConfig$$EnhancerBySpringCGLIB 실제 AppConfig가 아닌 조작된 자식

        System.out.println("bean = " + bean);

    }

}
