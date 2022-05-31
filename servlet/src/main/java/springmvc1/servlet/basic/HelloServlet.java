package springmvc1.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") //servlet url mapping
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //request
        // query parameter
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //response
        // http header
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        // http body
        response.getWriter().write("hello "+ username);
    }
}
