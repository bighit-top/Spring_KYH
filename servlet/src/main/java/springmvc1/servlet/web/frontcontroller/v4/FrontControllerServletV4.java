package springmvc1.servlet.web.frontcontroller.v4;

import springmvc1.servlet.web.frontcontroller.ModelView;
import springmvc1.servlet.web.frontcontroller.MyView;
import springmvc1.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import springmvc1.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import springmvc1.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    //프론트컨트롤러 호출 시 매핑정보를 세팅 해둔다
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return;
        }

        //파라미터 처리
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        //비지니스 로직
        String viewName = controller.process(paramMap, model);

        //뷰 네임 처리: 논리이름 -> 물리이름
        MyView view = viewResolver(viewName);

        view.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(
                        paramName -> paramMap.put(paramName, request.getParameter(paramName))
                );
        return paramMap;
    }
}
