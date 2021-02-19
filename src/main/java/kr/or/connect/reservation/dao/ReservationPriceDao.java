package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.ReservationPrice;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReservationPriceDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insert;
    private RowMapper<ReservationPrice> rowMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);

    public ReservationPriceDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_info_price")
                .usingGeneratedKeyColumns("id");
    }

    public Long addReservationPrice(ReservationPrice reservationPrice) {
        return insert.executeAndReturnKey(new BeanPropertySqlParameterSource(reservationPrice)).longValue();
    }

    public ReservationPrice findReservationPrice(Long reservationId) {
        try {
            Map<String, Long> map = new HashMap<>();
            map.put("id", reservationId);
            return jdbc.queryForObject(ReservationPriceDaoSqls.SELECT_BY_ID, map, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer deleteReservationsById(Long id) {
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return jdbc.update(ReservationPriceDaoSqls.DELETE_BY_ID, map);
    }
}
