package springmvc2.typeconverter1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springmvc2.typeconverter1.type.IpPort;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); //문자 타입
        Integer intValue = Integer.valueOf(data); //숫자로 변경
        System.out.println("intValue = " + intValue);
        return "OK";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "OK";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {

        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "OK";
    }
}
