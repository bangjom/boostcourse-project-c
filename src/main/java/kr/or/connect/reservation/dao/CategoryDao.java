package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CategoryDaoSqls.*;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;

import kr.or.connect.reservation.dto.Category;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
    private NamedParameterJdbcTemplate jdbc;

    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> selectAllWithProductCount() {
        return jdbc.query(SELECT_ALL_WITH_PRODUCT_COUNT, Collections.emptyMap(), (rs, rowNum) -> {
            Category category = new Category();
            category.setId(rs.getLong("id"));
            category.setName(rs.getString("name"));
            category.setCount(rs.getInt("COUNT(c.id)"));
            return category;
        });
    }

    public Integer selectCount() {
        return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
    }
}
