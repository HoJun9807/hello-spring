package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;

public class MemberService { // 비즈니스 로직 구현 클래스 (서비스 클래스) -> 회원 가입, 회원 조회 기능 구현
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안된다.
        validateDuplicateMember(member);
        // 회원 가입은 이름만으로 이루어진다.
        // 회원 가입이 정상적으로 이루어지면 회원 id를 반환한다.
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(Member member) { // 중복 회원 검증
        memberRepository.findByName(member.getName()) // Optional<Member>
                .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
