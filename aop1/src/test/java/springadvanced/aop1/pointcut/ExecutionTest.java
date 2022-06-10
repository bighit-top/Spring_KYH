package springadvanced.aop1.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import springadvanced.aop1.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod; //메서드 메타정보 저장용

    @BeforeEach
    public void init() throws NoSuchMethodException {
        //포인트컷 표현식에 사용할 메서드 정보
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        //public java.lang.String springadvanced.aop1.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }

    //정확한 포인트컷: (접근제어자? 반환타입 선언타입? 메서드이름 파라미터 예외?) - 물음표 생략 가능
    @Test
    void exactMatch() {
        pointcut.setExpression("execution(" +
                "public String springadvanced.aop1.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //모든 조건을 다 받는 가장 많이 생략한 포인트컷
    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))"); //모든 반환타입, 모든 메서드, 모든 파라미터
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //메서드 이름
    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))"); //hello 메서드만
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStart1() {
        pointcut.setExpression("execution(* hel*(..))"); //메서드 이름 패턴
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStart2() {
        pointcut.setExpression("execution(* *el*(..))"); //메서드 이름 패턴
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* no(..))"); //메서드 이름 패턴 실패의 경우
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    //패키지 이름
    @Test
    void packageExactMatch1() {
        pointcut.setExpression("execution(" +
                "* springadvanced.aop1.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2() {
        pointcut.setExpression("execution(" +
                "* springadvanced.aop1.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatchFalse() {
        pointcut.setExpression("execution(" +
                "* springadvanced.aop1.*.*(..))"); //패키지 하위를 제대로 포함 안한 경우
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* springadvanced.aop1.member..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* springadvanced.aop1..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //타입 매칭
    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(" +
                "* springadvanced.aop1.member.MemberServiceImpl.*(..))"); //구현체
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(" +
                "* springadvanced.aop1.member.MemberService.*(..))"); //인터페이스: 부모
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(" +
                "* springadvanced.aop1.member.MemberServiceImpl.*(..))"); //구현체 메서드(orverride X)
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(" +
                "* springadvanced.aop1.member.MemberService.*(..))"); //인터페이스 메서드(orverride X)
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    //파라미터
    //String 타입
    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //없음
    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    //갯수 하나, 모든 타입
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //갯수 무관, 모든 타입
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //String 타입으로 시작, 갯수 무관, 모든 타입
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
