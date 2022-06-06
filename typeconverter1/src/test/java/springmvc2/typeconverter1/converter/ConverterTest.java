package springmvc2.typeconverter1.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springmvc2.typeconverter1.type.IpPort;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("100");
        assertThat(result).isEqualTo(100);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(100);
        assertThat(result).isEqualTo("100");
    }

    @Test
    void ipPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void stringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort result = converter.convert("127.0.0.1:8080");
//        assertThat(result.getIp()).isEqualTo("127.0.0.1");
//        assertThat(result.getPort()).isEqualTo(8080);
        //@EqualsAndHashCode 주소값이 달라도 값을 비교
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
