package kr.or.connect.reservation.dao;

public class ReservationPriceDaoSqls {
    public static final String SELECT_BY_ID = "SELECT *  FROM  reservation_info_price WHERE id = :id";
    public static final String DELETE_BY_ID = "DELETE FROM reservation_info_price WHERE reservation_info_id = :id";

}
