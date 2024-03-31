package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// 스프링이 실행될 때 스프링 컨테이너에 이 클래스의 객체를 생성하여 넣어둔다.
// 스프링 컨테이너에서 스프링 빈이 관리된다.
// 스프링 빈이 되기 위해서는 @Controller, @Service, @Repository 등의 어노테이션이 필요하다.
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 MemberService를 가져와서 연결해준다.
    // DI (Dependency Injection) - 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입이 끝나면 홈 화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) { // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

// @Component : 스프링 빈으로 등록 // @Controller, @Service, @Repository는 @Component를 포함하고 있다.