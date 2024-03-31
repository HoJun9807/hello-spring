package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 인터페이스 다중 상속 가능
    // JpaRepository 인터페이스를 상속받으면 스프링 데이터 JPA가 자동으로 구현체를 만들어 빈으로 등록한다.

    @Override
    Optional<Member> findByName(String name);
    // 메소드 이름만으로 조회 기능을 제공한다.
    // 메소드 이름을 보고 JPQL을 생성한다.
    // JPQL: JPA가 제공하는 SQL을 추상화한 객체지향 쿼리 언어

}
// SpringDataJpaMemberRepository 인터페이스는 JpaRepository 인터페이스를 상속받는다.
// JpaRepository 인터페이스는 스프링 데이터 JPA가 제공하는 인터페이스로, 기본적인 CRUD 기능을 제공한다.
// JpaRepository 인터페이스를 상속받으면 스프링 데이터 JPA가 자동으로 구현체를 만들어 빈으로 등록한다.
// JpaRepository 인터페이스는 인터페이스를 상속받을 때 엔티티 타입과 ID 타입을 지정해야 한다.