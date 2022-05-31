package springmvc1.servlet.web.frontcontroller.v3;

import springmvc1.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
