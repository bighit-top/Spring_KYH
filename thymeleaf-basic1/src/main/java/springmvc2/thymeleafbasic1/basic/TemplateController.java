package springmvc2.thymeleafbasic1.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    //템플릿 조각
    @GetMapping("/fragment")
    public String template() {
        return "/template/fragment/fragmentMain";
    }

    //레이아웃: 헤더
    @GetMapping("/layout")
    public String layout() {
        return "/template/layout/layoutMain";
    }

    //레이아웃: 확장
    @GetMapping("/layoutExtend")
    public String layoutExtend() {
        return "template/layoutExtend/layoutExtendMain";
    }
}
