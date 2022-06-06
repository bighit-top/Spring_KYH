package springmvc2.exception1;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springmvc2.exception1.filter.LogFilter;
import springmvc2.exception1.interceptor.LogInterceptor;
import springmvc2.exception1.resolver.MyHandlerExceptionResolver;
import springmvc2.exception1.resolver.UserHandlerExceptionResolver;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //spring interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                //dispatcherType을 뺄 수 없음. 대신 오류 페이지 경로를 제외할 수 있음
                .excludePathPatterns("/css/**", "/*.ico", "/error", "/error-page/**");
    }

    //api error 처리: was에 보내는 정보, view에 보내는 정보 분리 -> 중간에 오류를 처리. 서블릿에서 재호출 하지 않음
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
        resolvers.add(new UserHandlerExceptionResolver());
    }

    //servlet filter
//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        //filter를 적용할 DispatcherType을 servlet에 등록: default request
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterRegistrationBean;
    }
}
