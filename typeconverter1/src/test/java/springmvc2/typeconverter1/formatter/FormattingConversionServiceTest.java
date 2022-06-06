package springmvc2.typeconverter1.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;
import springmvc2.typeconverter1.converter.IntegerToStringConverter;
import springmvc2.typeconverter1.converter.IpPortToStringConverter;
import springmvc2.typeconverter1.converter.StringToIntegerConverter;
import springmvc2.typeconverter1.converter.StringToIpPortConverter;
import springmvc2.typeconverter1.type.IpPort;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        //컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //포매터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        //컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        //포매터 사용
        String convertString = conversionService.convert(1000, String.class);
        assertThat(convertString).isEqualTo("1,000");

        Number convertNumber = conversionService.convert("1,000", Long.class);
        assertThat(convertNumber).isEqualTo(1000L);
    }
}
