package springmvc1.springmvcV1.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본, 복수 url 설정(or 방식)
     */
    @RequestMapping({"/hello-basic", "/hello-go/"})
    public String helloBasic() {
        log.info("helloBasic");
        return "OK~";
    }

    /**
     * http method 지정
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "OK~";
    }

    /**
     * http method 애노테이션 매핑
     *
     * @GetMaping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "OK~";
    }

    /**
     * PathVariable 사용: 변수명이 같으면 생략 가능
     *
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/{userId}
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId = {}", data);
        return "OK!";
    }

    /**
     * PathVariable 사용: 다중
     */
    @RequestMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터 추가 매핑: 조건
     * params="mode"
     * params="!mode"
     * params="mode=debug" : http://localhost:8080/mapping-param?mode=debug
     * params="mode!=debug"
     * params={"mode!=debug", "data=good"}: or 방식
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑: 조건
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입: consumes(요청에서의)
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE) //"application/json"
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "okay";
    }

    /**
     * 미디어 타입: produces(응답에서의)
     * Accept 헤더 기반 Media Type
     * produces="text/html"
     * produces="!text/html"
     * produces="text/*"
     * produces="*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)//"text/html"
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
