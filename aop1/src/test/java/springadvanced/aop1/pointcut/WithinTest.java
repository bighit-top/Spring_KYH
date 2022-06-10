package springadvanced.aop1.pointcut;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import springadvanced.aop1.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

//execution의 타입 부분만 정의 하는 것과 같음
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void withinExact() {
        pointcut.setExpression("within(springadvanced.aop1.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinStar() {
        pointcut.setExpression("within(springadvanced.aop1.member.*Service*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinSubPackage() {
        pointcut.setExpression("within(springadvanced.aop1..*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("타겟의 타입에만 직접 적용, 인터페이스를 선정하면 안됨")
    void withinSuperTypeFalse() {
        pointcut.setExpression("within(springadvanced.aop1.member.MemberService)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("execution은 타입 기반, 인터페이스 선정 가능")
    void executionSuperTypeTrue() {
        pointcut.setExpression("execution(* springadvanced.aop1.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
