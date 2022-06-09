package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("Message MessageDecorator 실행");

        //data -> ===== data =====
        String realResult = component.operation();
        String decoResult = "===== " + realResult + " =====";
        log.info("MessageDecorator 꾸미기 적용 전 ={}, 적용 후={}", realResult, decoResult);
        return decoResult;
    }
}
