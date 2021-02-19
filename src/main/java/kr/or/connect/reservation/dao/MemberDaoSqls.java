package kr.or.connect.reservation.dao;

public class MemberDaoSqls {
    public static final String SELECT_ALL_BY_EMAIL = "SELECT id, name, password, tel, email, create_date, modify_date FROM member WHERE email = :email";
    public static final String SELECT_BY_ID = "SELECT id, name, tel, email FROM member WHERE id = :id";
}
