package springmvc1.springmvcV1.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmvc1.springmvcV1.basic.HelloData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
@RestController
public class ResponseBodyController {

    /**
     * String
     */
    //servlet
    @GetMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("Servlet OK");
    }

    //HttpEntity<T> or ResponseEntity<T>
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyStringV2() {
        return new ResponseEntity<>("HttpEntity OK", HttpStatus.OK);
    }

    //@ResponseBody
//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyStringV3() {
        return "ResponseBody OK";
    }

    /**
     * JSON
     */
    //HttpEntity<T> or ResponseEntity<T>
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("hello");
        helloData.setAge(30);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //@ResponseBody
    @ResponseStatus(HttpStatus.OK) //객체는 상태코드를 반환하지 못하므로 상태코드 세팅을 따로 지원한다.
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("hello");
        helloData.setAge(30);
        return  helloData;
    }
}
