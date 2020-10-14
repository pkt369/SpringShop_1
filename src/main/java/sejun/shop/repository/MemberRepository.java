package sejun.shop.repository;

import sejun.shop.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id);
    Optional<Member> findByName(String name);
    Optional<Member> findByUser(String id, String pw);
}
