package kr.or.connect.reservation.dao;

public class UserDaoSqls {
    public static final String SELECT_ALL_BY_EMAIL = "SELECT id, name, password, phone, email, create_date, modify_date FROM user WHERE email = :email";
    public static final String SELECT_BY_ID = "SELECT id, name, phone, email FROM user WHERE id = :id";
}
