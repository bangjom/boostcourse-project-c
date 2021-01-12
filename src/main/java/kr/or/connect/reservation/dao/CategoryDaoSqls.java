package kr.or.connect.reservation.dao;

public class CategoryDaoSqls {
    public static final String SELECT_ALL_WITH_PRODUCT_COUNT = "SELECT c.id, name, COUNT(c.id) "
        + "FROM category AS c, product AS p, display_info AS d "
        + "WHERE c.id = p.category_id AND p.id = d.product_id GROUP BY c.id";
    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM category";
}
