package springmvc1.springmvcV1.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller //HTTP message body
public class RequestBodyStringController {

    //servlet
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream(); //http message body: byte code
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//byte->string

        log.info("messageBody = {}", messageBody);
        response.getWriter().write("OK");
    }

    //InputStream
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream,
                                    Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("OK");
    }

    //HttpEntity: http header, http body 정보 제공
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("OK");
    }

    //@RequestBody, @ResponseBody
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "OK";
    }
}
