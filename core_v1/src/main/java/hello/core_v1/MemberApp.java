package hello.core_v1;

import hello.core_v1.member.Grade;
import hello.core_v1.member.Member;
import hello.core_v1.member.MemberService;
import hello.core_v1.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        MemberService memberService = new MemberServiceImpl();

        //DI
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //DI 스프링 적용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //스프링 컨테이너. Bean으로 생성된 모든 객체를 관리함.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
