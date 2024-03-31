package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;


//@Repository // 스프링이 실행될 때 스프링 컨테이너에 이 클래스의 객체를 생성하여 넣어둔다.
public interface MemberRepository { // 회원 저장소 인터페이스 -> 회원 저장, 조회 기능 구현 (인터페이스)
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}