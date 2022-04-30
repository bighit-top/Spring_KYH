package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

/*
    private DataSource datasource;

    @Autowired
    public SpringConfig(DataSource datasource) {
        this.datasource = datasource;
    }
*/
/*
    // JPA 방식
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository); // spring data JPA 방식
    }

/*
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // DB 없이 jvm 메모리
//        return new JdbcMemberRepository(datasource); // DB 연결 구 방식
//        return new JdbcTemplateMemberRepository(datasource); // DB 연결 신 jdbctemplate 방식
//        return new JpaMemberRepository(em); // JPA 방식
        // spring data JPA는 필요없음
    }
*/

/*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
*/
}
