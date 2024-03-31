package hello.hellospring;



import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.SpringDataJpaMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 설정 클래스
public class SpringConfig {

    private final MemberRepository memberRepository; //5. 스프링 데이터 JPA 사용

    /*
    private final DataSource dataSource;
    private final EntityManager em; // JPA 사용

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em){
        this.dataSource = dataSource;
        this.em = em;
    }
    */

    /*.3 JdbcTemplate 사용
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    */


    @Bean // 스프링 빈 등록
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Autowired
    public SpringConfig(MemberRepository memberRepository) { //5. 스프링 데이터 JPA 사용
        this.memberRepository = memberRepository;
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

 /*
 @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository(); //1. 메모리 저장소 사용
        //return new JdbcMemberRepository(dataSource); //2. JDBC 저장소 사용
        //return new JdbcTemplateMemberRepository(dataSource); //3. JdbcTemplate 사용
        //return new JpaMemberRepository(em); //4. JPA 사용
        // 스프링 설정 파일에서 MemoryMemberRepository를 JdbcMemberRepository로 변경
        // 개방-폐쇄 원칙(OCP): 확장에는 열려있고 수정에는 닫혀있다.
        // 기존 코드를 전혀 손대지 않고, 설정(Config)만으로 구현 클래스를 변경할 수 있다.
        // DB에 저장하므로 스프링 서버를 다시 실행해도 데이터가 유지된다.
    }
    */

}
// 여기서는 자바 코드로 직접 스프링 빈을 등록하는 방법을 사용했다.

