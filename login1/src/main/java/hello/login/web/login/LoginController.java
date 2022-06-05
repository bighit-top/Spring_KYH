package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    //쿠키 사용
//    @PostMapping("/login")
    public String loginV1(@ModelAttribute @Valid LoginForm form, //form
                        BindingResult bindingResult, //binding 및 error 처리
                        HttpServletResponse response) { //cookie를 담기 위해

        //검증
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //로그인 시도
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        //로그인 실패
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공
        //쿠키에 시간 정보를 주지 않으면 세샨 쿠키(브라우저 종료시 모두 종료됨)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }

    //세션 만들어 보기
//    @PostMapping("/login")
    public String loginV2(@ModelAttribute @Valid LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response) {

        //검증
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //로그인 시도
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        //로그인 실패
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공
        //세션 관리자를 통해 세션 생성, 회원 데이터를 보관
        sessionManager.createdSession(loginMember, response);
        return "redirect:/";
    }

    //서블릿 세션
//    @PostMapping("/login")
    public String loginV3(@ModelAttribute @Valid LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request) { //servlet session 사용

        //검증
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //로그인 시도
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        //로그인 실패
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(); //default true: 없으면 생성, false: 없으면 null
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    //필터
    @PostMapping("/login")
    public String loginV4(@ModelAttribute @Valid LoginForm form,
                          BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request) { //servlet session 사용

        //검증
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //로그인 시도
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        //로그인 실패
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(); //default true: 없으면 생성, false: 없으면 null
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    //쿠키 사용
//    @PostMapping("/logout")
    public String logoutV1(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/";
    }

    //세션 만들어보기
//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }

    //서블릿 세션
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //session을 비움
        }
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
