package springadvanced.advanced1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springadvanced.advanced1.trace.logtrace.LogTrace;
import springadvanced.advanced1.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
