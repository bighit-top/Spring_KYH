package springmvc2.exception1.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {

        log.info("call resolver", ex);

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage()); //api error 처리: 상태코드를 바꿀 수 있음
                return new ModelAndView(); //정상 처리로 반환, 서블릿에 보낼 오류 정보와 뷰에 보낼 정보를 분리. 예외를 먹어버림.
            }
        } catch (IOException e) {
            log.info("resolver ex", e);
        }
        return null; //다음 ExceptionResolver를 찾아 실행, 없으면 예외처리가 안되고 기존 예외를 서블릿 밖으로 던짐
        // 이 코드에서 IllegalArgumentException이 잡히지 않으면 결국 다시 500으로 WAS에 넘김
    }
}
