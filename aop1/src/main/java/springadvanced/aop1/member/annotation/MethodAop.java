package springadvanced.aop1.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //method용 annotation
@Retention(RetentionPolicy.RUNTIME) //runtime 동안 annotation 유지
public @interface MethodAop {
    String value();
}
