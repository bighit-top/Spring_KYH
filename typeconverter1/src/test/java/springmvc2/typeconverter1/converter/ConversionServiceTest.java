package springmvc2.typeconverter1.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import springmvc2.typeconverter1.type.IpPort;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        //등록
        DefaultConversionService conversionService = new DefaultConversionService(); //인터페이스 구현체
        conversionService.addConverter(new StringToIntegerConverter()); //converter 등록
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //사용
        Assertions.assertThat(conversionService.convert("10", Integer.class))
                .isEqualTo(10);
        Assertions.assertThat(conversionService.convert(10, String.class))
                .isEqualTo("10");

        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        Assertions.assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        Assertions.assertThat(ipPortString).isEqualTo("127.0.0.1:8080");
    }
}
