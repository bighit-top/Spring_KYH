package springmvc1.springmvcV1.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    //회원 목록 조회
    @GetMapping
    public String users() {
        return "get users: 회원 목록 조회";
    }

    //회원 등록
    @PostMapping
    public String addUser() {
        return "post users: 회원 등록";
    }

    //회원 조회
    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId) {
        return "get userId="+ userId +" : 회원 조회";
    }

    //회원 수정
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId="+ userId +" : 회원 수정";
    }

    //회원 삭제
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId="+ userId +" : 회원 삭제";
    }
}
