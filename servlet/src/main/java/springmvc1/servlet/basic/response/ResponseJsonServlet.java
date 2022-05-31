package springmvc1.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import springmvc1.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2. 응답
 * JSON
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json(;charset=utf-8)
        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("Hello");
        helloData.setAge(30);

        //object -> json
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
