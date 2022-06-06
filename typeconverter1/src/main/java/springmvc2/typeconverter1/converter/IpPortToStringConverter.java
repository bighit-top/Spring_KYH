package springmvc2.typeconverter1.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import springmvc2.typeconverter1.type.IpPort;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("convert source={}", source);
        //IpPort 객체 -> string
        return source.getIp() + ":" + source.getPort();
    }
}
