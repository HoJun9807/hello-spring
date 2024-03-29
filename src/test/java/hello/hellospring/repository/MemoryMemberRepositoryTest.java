package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions;

import hello.hellospring.domain.Member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 테스트가 끝날 때마다 실행되는 코드
    public void afterEach() {
        repository.clearStore();
        // 테스트가 끝날 때마다 저장소를 비워주는 코드
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("hojun");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Optional에서 값을 꺼낼 때는 get()을 사용
        assertThat(member).isEqualTo(result);
        // member와 result가 같은지 확인
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        repository.save(member3);

        assertThat(repository.findAll().size()).isEqualTo(3);
        // findAll의 결과가 3개인지 확인
    }
}
