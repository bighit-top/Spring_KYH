package springmvc2.exception1;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 오류 페이지 커스텀 기능
 * 서블릿 컨테이너
 */
//@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        //was에서 오류 발생 시 오류 페이지 재요청(서버내부에서 오류정보를 포함해서 - Request attribute)
        //컨트롤러 호출 2회: 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러
        //HTTP 상태코드 오류 발생 시 오류 페이지 이동
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

        //RuntimeException 발생 시 오류 페이지 이동
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
    }
}
