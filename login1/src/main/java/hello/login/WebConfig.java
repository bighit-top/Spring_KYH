package hello.login;

import hello.login.web.argumentresolver.LoginMemberArgumentResolver;
import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LoginCheckInterceptor;
import hello.login.web.interceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

//spring 설정 등록: filter, interceptor
@Configuration
public class WebConfig implements WebMvcConfigurer { //WebMvcConfigurer: Spring interceptor 등록

    //@Login 등록
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    //Spring interceptor 등록: spring 제공 룰
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) //만든 스프링 인터셉터 주입
                .order(1) //interceptor 순서
                .addPathPatterns("/**") //적용할 호출 경로: 루트 하위로 적용
                .excludePathPatterns("/css/**", "/*.ico", "/error"); //적용 제외 호출 경로

        registry.addInterceptor(new LoginCheckInterceptor()) //만든 스프링 인터셉터 주입
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout",
                        "/css/**", "/*.ico", "/error");
    }

//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); //만든 필터 주입
        filterRegistrationBean.setOrder(1); //filter chain 순서
        filterRegistrationBean.addUrlPatterns("/*"); //filter를 설정할 요청 url 패턴

        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter()); //만든 필터 주입
        filterRegistrationBean.setOrder(2); //filter chain 순서
        filterRegistrationBean.addUrlPatterns("/*"); //filter를 설정할 요청 url 패턴

        return filterRegistrationBean;
    }
}
