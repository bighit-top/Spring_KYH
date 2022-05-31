package springmvc1.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //component scan 대상, requestmappinghandlermapping 대상
//@Component
//@RequestMapping
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") //요청정보 매핑
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
