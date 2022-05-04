package hello.core_v1;

import hello.core_v1.member.MemberRepository;
import hello.core_v1.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //스프링 빈을 자동으로 다 긁어서 스프링컨테이너에 등록한다. @Component 붙어있는 것들 대상으로.
//        basePackages = "hello.core_V1.member", //스캔 대상 시작 폴더를 지정해줌
//        basePackageClasses = AutoAppConfig.class, //스캔 대상 시작 패키지를 지정해줌
        //제외할 빈 설정 : Configuration.class - 수동 등록과 충돌을 막기 위해 기존 AppConfig 등록 방지(임시 공부용으로 설정)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

        //default : 지징이 없는 경우 현재 패키지 hello.core_v1 하위로 모두 뒤진다.
)
public class AutoAppConfig {
    //@ComponentScan이 자동으로 주입해줄 것이기에 코드 작성은 없다.


    //수동 빈 등록 : 자동 빈 등록과 이름이 충돌하면 수동 빈이 우선권을 가지고 overriding 된다. - 오류를 찾기 굉장히 어렵다.
    // 스프링부트 최근 업데이트에서 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본값이 변경되었다.
/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/
}
