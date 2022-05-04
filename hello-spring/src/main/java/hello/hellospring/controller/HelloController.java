package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 컨텐츠
    @GetMapping("hello") // get방식
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // hello.html 페이지로 넘겨줌
    }

    // MVC와 템플릿 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식 - 문자
    @GetMapping("hello-string")
    @ResponseBody // http의 body에 이 데이터를 직접 넣겠다는 것 - 문자면 문자 그대로
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }


    // API 방식 - 객체
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}