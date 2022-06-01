package springmvc1.springmvcV1.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {

    //@Slf4j와 같음
    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //System.out: 실무에서 사용 X(안됨) -> log 사용
        //레벨 설정할 수 없고 무조건 출력
        System.out.println("name = " + name);

        //log(level) -> application.properties 에서 레벨 설정
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        //문자열의 연산이 리소스로 사용됨. trace를 사용하지 않더라도 리소스 낭비 -> 사용 X
//        log.trace("trace my log = " + name);

        //@Controller -> view resolver 이름 반환: 뷰 렌더링
        //@RestController -> string 문자 반환: HTTP 메시지 바디에 입력
        return "OK!";
    }
    
}
