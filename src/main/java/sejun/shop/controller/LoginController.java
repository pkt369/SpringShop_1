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
        return "login/login";
    }

    @GetMapping("/join")
    public String join() {
        return "login/join";
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

    @PostMapping("/login/check")
    public String postLogin(LoginForm form, Model model){
        Optional<Member> user = memberService.login(form.getId(), form.getPw());

        //존재하지 않는다면
        if(!user.isPresent()){
            return "redirect:/login";
        }
        //존재한다면
        model.addAttribute("name", user.get().getName()); //이름
        model.addAttribute("loginCheck",true); //로그인 성공
        return "redirect:/";
    }
}
