package hello.core_v1;

import hello.core_v1.discount.DiscountPolicy;
import hello.core_v1.discount.RateDiscountPolicy;
import hello.core_v1.member.MemberRepository;
import hello.core_v1.member.MemberService;
import hello.core_v1.member.MemberServiceImpl;
import hello.core_v1.member.MemoryMemberRepository;
import hello.core_v1.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//관심사를 분리하기 위해 한 곳에서 구현체 주입을 모두 설정해준다.
//해당 클래스내에서 변경하면 다른 serviceImpl 등 클라이언트 코드 변경이 필요없다.
@Configuration //스프링 설정정보
public class AppConfig {

/*
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderServiceImpl orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
*/

    //리펙토링 - 역할과 구현이 확실히 보이도록. 메서드명만 봐도 확인됨.
    @Bean //스프링 컨테이너에 등록
    public MemberService memberService() {
//        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderServiceImpl orderService() {
//        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); //정액할인
        return new RateDiscountPolicy(); //정률할인
    }

    //@Configuration 에 관하여 : 스프링 컨테이너가 Bean 생성에 관여하여 싱글톤을 유지시킨다.
    //ConfigurationSingletonTest : AppConfig.memberRepository
    // expected : AppConfig.memberRepository 3번
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    // result : AppConfig.memberRepository 1번
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService


    //@Configuration 이 없어도 @Bean 등록은 되지만 순수 자바 코드가 작동하여 싱글톤이 해제된다.
    // result
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    //@Autowired MemberRepository memberRepository; 로 선언하여 주입해주면 싱글톤 가능은 하다. 이미 등록된거 가지고 끌어다 쓴다.


}
