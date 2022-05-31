package springmvc1.servlet.web.springmvc.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc1.servlet.domain.member.Member;
import springmvc1.servlet.domain.member.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form", method = RequestMethod.GET) //GET 요청만 처리
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST) //POST
    @PostMapping("/save")
    public String save(@RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model){

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

//    @RequestMapping(method = RequestMethod.GET) //GET
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
