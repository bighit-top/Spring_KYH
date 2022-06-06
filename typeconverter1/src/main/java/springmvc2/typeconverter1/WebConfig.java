package springmvc2.typeconverter1;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springmvc2.typeconverter1.converter.IntegerToStringConverter;
import springmvc2.typeconverter1.converter.IpPortToStringConverter;
import springmvc2.typeconverter1.converter.StringToIntegerConverter;
import springmvc2.typeconverter1.converter.StringToIpPortConverter;
import springmvc2.typeconverter1.formatter.MyNumberFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //converter, formatter 등록
    //우선순위: converter > formatter
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        registry.addFormatter(new MyNumberFormatter());
    }

}
