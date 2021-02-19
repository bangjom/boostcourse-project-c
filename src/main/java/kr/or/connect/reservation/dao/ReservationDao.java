package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.Order;
import kr.or.connect.reservation.dto.Reservation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ReservationDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insert;
    private RowMapper<Reservation> rowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);

    public ReservationDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_info")
                .usingGeneratedKeyColumns("id");
    }

    public Long addReservation(Reservation reservation) {
        return insert.executeAndReturnKey(new BeanPropertySqlParameterSource(reservation)).longValue();
    }

    public Reservation findReservationById(Long reservationId) {
        try{
            Map<String, Long> map = new HashMap<>();
            map.put("id", reservationId);
            return jdbc.queryForObject(ReservationDaoSqls.SELECT_BY_ID, map, rowMapper);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Order> findOrdersByUserEmail(String email) {
        try{
            Map<String, String> map = new HashMap<>();
            map.put("email", email);
            return jdbc.query(ReservationDaoSqls.SELECT_BY_USER_EMAIL,map,BeanPropertyRowMapper.newInstance(Order.class));
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer deleteReservationsById(Long id){
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return jdbc.update(ReservationDaoSqls.DELETE_BY_ID, map);
    }

    public Integer cancelReservationsById(Long reservationId) {
        Map<String, Long> map = new HashMap<>();
        map.put("id", reservationId);
        return jdbc.update(ReservationDaoSqls.CANCEL_BY_ID, map);
    }
}
