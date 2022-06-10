package springadvanced.aop1.member;

import org.springframework.stereotype.Component;
import springadvanced.aop1.member.annotation.ClassAop;
import springadvanced.aop1.member.annotation.MethodAop;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "OK";
    }

    public String internal(String param) {
        return "OK";
    }
}
