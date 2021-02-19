package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
    public static final String SELECT_BY_ID = "SELECT * FROM reservation_info WHERE id = :id";
    public static final String SELECT_BY_USER_EMAIL = "SELECT r.id, r.product_id, r.display_info_id, r.cancel_flag, " +
            "p.description AS product_description, p.content AS product_content, m.id AS user_id, rp.count * pp.price AS sum_price, " +
            "r.reservation_date, r.create_date, r.modify_date FROM member AS m, reservation_info AS r, " +
            "reservation_info_price AS rp, product_price AS pp, product AS p  " +
            "WHERE r.id = rp.reservation_info_id AND rp.product_price_id = pp.id AND pp.product_id = p.id  AND " +
            "m.email = r.reservation_email AND m.email = :email";
    public static final String DELETE_BY_ID = "DELETE FROM reservation_info WHERE id = :id";
    public static final String CANCEL_BY_ID = "UPDATE reservation_info SET cancel_flag = 1 WHERE id = :id";
}
