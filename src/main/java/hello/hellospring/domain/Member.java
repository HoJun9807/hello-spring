package hello.hellospring.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // JPA가 관리하는 엔티티
public class Member { // 회원 도메인 클래스 -> 회원 정보를 담는 클래스 (도메인 클래스)

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 생성해주는 것
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
