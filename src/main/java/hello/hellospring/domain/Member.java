package hello.hellospring.domain;

public class Member { // 회원 도메인 클래스 -> 회원 정보를 담는 클래스 (도메인 클래스)

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
