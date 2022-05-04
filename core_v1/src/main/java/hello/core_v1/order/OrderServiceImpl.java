package hello.core_v1.order;

import hello.core_v1.annotation.MainDiscountPolicy;
import hello.core_v1.discount.DiscountPolicy;
import hello.core_v1.discount.FixDiscountPolicy;
import hello.core_v1.discount.RateDiscountPolicy;
import hello.core_v1.member.Member;
import hello.core_v1.member.MemberRepository;
import hello.core_v1.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //자동 생성자 세팅
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //정액할인
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //정률할인
    //한 줄만 바뀌면 되지만 실제 코드를 수정해야함.
    //DiscountPolicy 인터페이스에만 의존하는 것 같지만 실제 각 구현체 Fix, Rate에도 의존하고 있는 것. OCP, DIP 위반

    //해결 코드 - 누군가 대신 구현체를 생성해서 주입해주어야 한다. : 생성자를 통해 구현체를 주입시킨다.
    //3. 필드 주입 : 외부에서 변경하지 못하며, 테스트하기 어려워 권장하지 않는다.
    // 테스트코드 자체에서의 사용은 괜찮. config 등 특수한 경우도.
//    @Autowired
    private final MemberRepository memberRepository;
//    @Autowired
    private final DiscountPolicy discountPolicy;

    //DI 방법
    //1. 생성자 주입 : 생성자의 인수는 필수 불변요소라 없으면 안됨. 생성자주입이 관례다.
    //불변, 누락 final키워드 - 컴파일 시점에 오류를 잡을 수 있음.
//    @Autowired //생성자 하나는 자동 빈 등록
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

        //빈 하나에 타입이 중복될 때 : DiscountPolicy - fixDiscountPolicy, rateDiscountPolicy
        // 1. @Autowired 기능에서 필드명, 파라미터명으로 인식해서 해당 타입으로 빈 이름을 매칭해준다.
        // 2. @Qualifier("name") 매칭 지정
        // : @Qualifier로 name지정된게 없으면 빈 이름에서 찾는다. 따라서 명확하게 양쪽에 @Qualifier 세팅하는게 좋다.
        // 3. @Primary 빈 매칭에 타입 중복 시 매칭 우선순위로 지정 : 자주 사용됨
        // 2-1. Annotation 생성해서 활용 @MainDiscountPolicy
    }

    //2. 수정자(setter) 주입 : 선택적 의존관계 주입
/*
//    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
//    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
*/

    //4. 메서드 주입 : 수정자 주입과 같음. 일반 메서드일 뿐. 한번에 여러 필드 주입이 가능. 잘 사용하지 않음.
/*
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //정액할인이든 정률할인이든 상관없게 잘 구현된 설계

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
