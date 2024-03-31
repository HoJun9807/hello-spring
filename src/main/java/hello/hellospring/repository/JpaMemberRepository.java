package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // JPA는 EntityManager로 모든 것이 동작

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    } // EntityManager를 주입받아서 사용

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    } // persist: 영속성 컨텍스트에 member를 넣는다.
    // 영속성 컨텍스트: 엔티티를 영구 저장하는 환경
    // persist를 하면 member를 영속성 컨텍스트에 저장하고 트랜잭션이 커밋되는 시점에 DB에 반영
    // persist를 하면 member의 id가 자동으로 생성되어 member에 할당된다.


    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }
}
