package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import kr.or.connect.reservation.dto.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insert;
    private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);

    public UserDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insert = new SimpleJdbcInsert(dataSource)
            .withTableName("user");
    }

    public User getMemberByEmail(String email){
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.queryForObject(UserDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }

    public User getMemberById(Long id){
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);

        return jdbc.queryForObject(UserDaoSqls.SELECT_BY_ID, params, rowMapper);
    }

    public void addMember(User user) {
        insert.execute(new BeanPropertySqlParameterSource(user));
    }
}
