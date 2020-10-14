package sejun.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sejun.shop.domain.Member;
import sejun.shop.repository.JdbcMemberRepository;

import java.util.Optional;

@Service
public class MemberService {

    private final JdbcMemberRepository memberRepository;

    @Autowired
    public MemberService(JdbcMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    public void join(Member member){
        validateDuplicateMember(member);
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
}
