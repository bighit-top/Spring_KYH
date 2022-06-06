package springmvc2.exception1.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmvc2.exception1.exception.UserException;
import springmvc2.exception1.exhandler.ErrorResult;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

/*
    */
/**
     * ExceptionHandler
     * 현재 컨트롤러에서 발생하는 Exception을 원하는 타입(ErrorResult)으로 반환 처리해줌
     * 지정한 예외(부모), 자식 예외(우선)
     *//*

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
*/

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {

        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
