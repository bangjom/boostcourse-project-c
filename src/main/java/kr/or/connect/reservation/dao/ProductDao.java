package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ProductDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import kr.or.connect.reservation.dto.Product;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Product> selectAllByCategory(Long categoryId, Integer start, Integer limit) {
        Map<String, Integer> params = new HashMap<>();
        params.put("categoryId", categoryId.intValue());
        params.put("start", start);
        params.put("limit", limit);
        return jdbc.query(SELECT_PAGING, params, rowMapper);
    }

    public Integer selectCountByCategory(Long categoryId) {
        Map<String, Long> params = new HashMap<>();
        params.put("categoryId", categoryId);
        return jdbc.queryForObject(SELECT_COUNT, params, Integer.class);
    }

    public Product selectOneById(Long displayId) {
        Map<String, Long> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.queryForObject(SELECT_BY_DISPLAY_ID, params, rowMapper);
    }
}
