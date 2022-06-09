package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component messageDecoComponent = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecoComponent);
        client.execute();
    }

    @Test
    void decorator2() {
        Component component = new RealComponent();
        Component messageDecoComponent = new MessageDecorator(component);
        Component timeDecoComponent = new TimeDecorator(messageDecoComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecoComponent);
        client.execute();
    }
}
