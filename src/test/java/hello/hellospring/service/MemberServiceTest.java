package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService; // DI (Dependency Injection)
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 테스트가 실행되기 전에 MemoryMemberRepository와 MemberService를 생성한다.

    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
        // 테스트가 끝날 때마다 저장소를 비운다.
    }

    @Test
    void join() {
        // given (이런 상황이 주어졌을 때) -> 테스트를 실행하기 위한 준비
        Member member = new Member();
        member.setName("hello");

        // when (이렇게 하면) -> 테스트 실행 (실제로 어떤 동작을 하는지)
        Long saveId = memberService.join(member);
        // 회원 가입을 하고 회원 id를 반환한다.

        // then (이렇게 된다) -> 결과 확인 (검증)
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
        // member의 이름과 findMember의 이름이 같은지 확인
        // assertEquals(a, b) -> a와 b가 같은지 확인
    }

    @Test
    public void joinException() {
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        memberService.join(member1);
        // member1을 저장

        /*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        }*/

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));
        // member2를 저장하면 IllegalStateException이 발생해야 한다.
        // assertThrows(예외 클래스, 람다식) -> 예외가 발생하는지 확인

        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        // 예외 메시지가 "이미 존재하는 회원입니다."인지 확인
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}