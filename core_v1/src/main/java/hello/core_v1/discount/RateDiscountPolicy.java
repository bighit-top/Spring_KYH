package hello.core_v1.discount;

import hello.core_v1.annotation.MainDiscountPolicy;
import hello.core_v1.member.Grade;
import hello.core_v1.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") //빈 매칭에 타입 중복 시 이름 설정
//@Primary //빈 매칭에 타입 중복 시 매칭 우선순위로 지정
@MainDiscountPolicy //annotation 생성하여 활용
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
