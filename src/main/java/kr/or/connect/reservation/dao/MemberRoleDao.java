package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.connect.reservation.dto.MemberRole;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRoleDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insert;

    private RowMapper<MemberRole> rowMapper = BeanPropertyRowMapper.newInstance(MemberRole.class);

    public MemberRoleDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insert = new SimpleJdbcInsert(dataSource)
            .withTableName("member_role");
    }

    public List<MemberRole> getRolesByEmail(String email){
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.query(MemberRoleDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }

    public void addRole(MemberRole memberRole) {
//        Map map = new ObjectMapper().convertValue(memberRole, Map.class);
//        map.remove("id");
        insert.execute(new BeanPropertySqlParameterSource(memberRole));
    }
}
