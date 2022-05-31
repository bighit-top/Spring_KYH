package springmvc1.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 1. 파라미터 전송 기능
 * API message body: text
 */
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //http body 내용을 byte 코드로 바로 획득
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//byte -> string 변환

        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("OK!");
    }
}
