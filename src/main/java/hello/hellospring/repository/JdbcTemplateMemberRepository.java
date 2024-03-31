package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired // 생성자가 하나일 경우 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // SimpleJdbcInsert: 쿼리문을 직접 작성하지 않고 간단하게 insert를 할 수 있게 해준다.
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }
    // insert: 저장, 첫 번째 인자는 SQL 쿼리, 두 번째 인자는 SQL에 바인딩할 값

    @Override
    public Optional<Member> findById(Long id) { // id로 회원 조회
        List<Member> result = jdbcTemplate.query("select * from member where id = ?",
            memberRowMapper(), id);
        return result.stream().findAny();
    }
    // query: 조회, 첫 번째 인자는 SQL 쿼리, 두 번째 인자는 RowMapper, 세 번째 인자는 SQL에 바인딩할 값

    @Override
    public Optional<Member> findByName(String name) { // 이름으로 회원 조회
        List<Member> result = jdbcTemplate.query("select * from member where name = ?",
            memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { // 모든 회원 조회
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }


    private RowMapper<Member> memberRowMapper() {
        // RowMapper: DB에서 가져온 데이터를 객체로 매핑

        /*return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };*/
        // 람다식으로 변경
        return (rs, rowNum) -> {

            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
