package sejun.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sejun.shop.domain.Member;
import sejun.shop.service.MemberService;

import java.util.Optional;

@Controller
public class LoginController {

    private final MemberService memberService;

    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String login(){
        return "/login/login";
    }

    @GetMapping("/join")
    public String join() {
        return "/login/join";
    }

    @PostMapping("/join/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        member.setId(form.getId());
        member.setPw(form.getPw());


        memberService.join(member);

        return "redirect:/login";
    }


}
