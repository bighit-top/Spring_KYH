package springmvc2.exception1.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springmvc2.exception1.exception.UserException;
import springmvc2.exception1.exhandler.ErrorResult;

@Slf4j
@RestControllerAdvice(basePackages = "springmvc2.exception1.api") //여러 컨트롤러 오류를 공통으로 처리해줌, 지정없으면 전체 컨트롤 공통
public class ExControllerAdvice {

    /**
     * ExceptionHandler
     * 컨트롤러에서 발생하는 Exception을 원하는 타입(ErrorResult)으로 반환 처리해줌
     * 지정한 예외(부모), 자식 예외(우선)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST) //ExceptionHandler로 예외를 잡으면 200처리가 되므로 상태코드를 세팅해준다
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) { //파라미터 예외 지정
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) { //Exception 위에서 잡지 못한 오류까지 공통으로 처리해줌
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부오류");
    }

}
