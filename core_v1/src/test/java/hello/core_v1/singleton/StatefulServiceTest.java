package hello.core_v1.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

/*
        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        statefulService1.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); //20000원. 교체되버림.
*/
        //지역변수 사용으로 공유를 막는다.
        //ThreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        System.out.println("userAPrice = " + userAPrice); //10000
        System.out.println("userBPrice = " + userBPrice); //20000

        Assertions.assertThat(userAPrice).isNotSameAs(userBPrice);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }


}