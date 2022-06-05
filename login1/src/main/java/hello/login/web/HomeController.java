package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String home() {
        return "/home";
    }

    //쿠키 사용
//    @GetMapping("/")
    public String homeLoginV1(@CookieValue(name = "memberId", required = false) Long memberId,
                            Model model) {
        //쿠키 체크
        if (memberId == null) {
            return "home";
        }

        //로그인 실패
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        //로그인 성공
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //세션 만들어보기
//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        //세션 관리자에 저장된 회원 정보 조회
        Member member = (Member) sessionManager.getSession(request);
        if (member == null) {
            return "home";
        }

        //로그인 성공
        model.addAttribute("member", member);
        return "loginHome";
    }

    //서블릿 세션
//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        //세션 체크
        HttpSession session = request.getSession(false); //기본 true라 비회원도 무조건 만듦. 자동생성 막도록 false
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원 데이터가 없으면 home으로 이동
        if (loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인 성공
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //스프링 세션 attribute
//    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model) {

        //세션에 회원 데이터가 없으면 home으로 이동
        if (loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인 성공
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //ArgumentResolver
    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(
            @Login Member loginMember, //@Login 등록하여 세션 관리
            Model model) {

        //세션에 회원 데이터가 없으면 home으로 이동
        if (loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인 성공
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}