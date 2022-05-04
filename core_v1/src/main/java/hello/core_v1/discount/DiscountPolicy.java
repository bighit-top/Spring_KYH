package hello.core_v1.discount;

import hello.core_v1.member.Member;

public interface DiscountPolicy {

    /**
     * 할인 대상 금액
     * @param member
     * @param price
     * @return
     */
    int discount(Member member, int price);

}
