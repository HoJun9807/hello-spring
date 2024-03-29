package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    // 회원 저장

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null이 반환될 수 있으므로 Optional로 감싸서 반환
    }
    // id로 회원 찾기

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 루프를 돌면서
                .filter(member -> member.getName().equals(name)) // name이 같은지 확인
                .findAny(); // 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store에 있는 모든 값 반환
    }

    public void clearStore() {
        store.clear();
        // 저장소 비우기 (테스트용)
    }

}
