package springmvc1.servlet.web.frontcontroller.v3;

import springmvc1.servlet.web.frontcontroller.ModelView;
import springmvc1.servlet.web.frontcontroller.MyView;
import springmvc1.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import springmvc1.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import springmvc1.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    //프론트컨트롤러 호출 시 매핑정보를 세팅 해둔다
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return;
        }

        //파라미터 처리
        Map<String, String> paramMap = createParamMap(request);

        //비지니스 로직
        ModelView mv = controller.process(paramMap);

        //뷰 네임 처리: 논리이름 -> 물리이름
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
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
