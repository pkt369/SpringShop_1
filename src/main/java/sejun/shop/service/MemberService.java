package sejun.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sejun.shop.domain.Member;
import sejun.shop.domain.Product;
import sejun.shop.repository.JdbcMemberRepository;
import sejun.shop.repository.JdbcProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    private final JdbcMemberRepository memberRepository;


    @Autowired
    public MemberService(JdbcMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    /*
    * 회원가입
    * */
    public void join(Member member){
        validateDuplicateMember(member); //중복체크
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPw(passwordEncoder.encode(member.getPw()));

        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다");
                });
    }

    /*
    * 로그인
    * */
    public Optional<Member> login(String id, String pw){
        return memberRepository.findByUser(id, pw);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> userWrapper = memberRepository.findById(userId);
        Member user = userWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new User(user.getId(), user.getPw(), authorities);
    }
}
