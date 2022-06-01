package springmvc1.springmvcV1.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springmvc1.springmvcV1.basic.HelloData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller //쿼리파라미터, HTML Form
public class RequestParamController {

    //쿼리 파라미터, HTML Form
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("v1");
    }

    //@RequestParam 1
    @ResponseBody //http body에 넣어서 반환: view resolver X
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "RequestParam v2";
    }

    //@RequestParam 2 -> 변수명이 같으면 생략가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "RequestParam v3";
    }

    //datatype이 단순 타입이면 @RequestParam 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "RequestParam v4";
    }

    //@RequestParam 속성: required(default=true)
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) { //int형은 null을 받지 못함
        log.info("username={}, age={}", username, age);
        return "RequestParam attribute: required";
    }

    //@RequestParam 속성: defaultValue
    // 넘어온 값이 없으면 입력 -> null,"" 모두 처리
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username={}, age={}", username, age);
        return "RequestParam attribute: defaultValue";
    }

    //@RequestParam: Map
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "RequestParam Map";
    }

    //@ModelAttribute: before
    @ResponseBody
    @RequestMapping("/model-attribute-before")
    public String modelAttributeBefore(@RequestParam String username, @RequestParam Integer age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "before ModelAttribute";
    }

    //@ModelAttribute
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ModelAttribute v1";
    }

    //@ModelAttribute: 생략
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ModelAttribute v2";
    }
}
