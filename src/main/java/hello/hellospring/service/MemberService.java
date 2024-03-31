package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service // 스프링이 실행될 때 스프링 컨테이너에 이 클래스의 객체를 생성하여 넣어둔다.
@Transactional // JPA를 사용할 때 모든 데이터 변경이 트랜잭션 안에서 실행되어야 한다.
public class MemberService { // 비즈니스 로직 구현 클래스 (서비스 클래스) -> 회원 가입, 회원 조회 기능 구현
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) { // 회원 가입

/*
    시간 측정 로직 AOP
    핵심 관심 사항이 아니다.
    모든 메소드의 호출 시간을 측정하고 싶다면?
    공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
    회원 가입 시간, 회원 조회 시간을 측정하고 싶다면?
*/
    // AOP를 사용하지 않고 직접 시간 측정 코드를 작성
    // 아래와 같이 직접 시간 측정 코드를 작성하면 모든 메소드에 시간 측정 코드를 추가해야 한다.
       /*
       long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 중복 회원 검증
            // 같은 이름이 있는 중복 회원은 안된다.
            memberRepository.save(member); // 회원 가입
            // 회원 가입은 이름만으로 이루어진다.
            // 회원 가입이 정상적으로 이루어지면 회원 id를 반환한다.
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
        */

        validateDuplicateMember(member);
        return memberRepository.save(member).getId();


    }

    private void validateDuplicateMember(Member member) { // 중복 회원 검증
        memberRepository.findByName(member.getName()) // Optional<Member>
                .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }



    public List<Member> findMembers() { // 전체 회원 조회

    // AOP를 사용하지 않고 직접 시간 측정 코드를 작성
    // 아래와 같이 직접 시간 측정 코드를 작성하면 모든 메소드에 시간 측정 코드를 추가해야 한다.
       /*
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
        */

        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) { // 회원 조회
        return memberRepository.findById(memberId);
    }
}
