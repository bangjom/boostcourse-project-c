package kr.or.connect.reservation.dao;

public class PromotionDaoSqls {
    public static final String SELECT_ALL =
        "SELECT pm.id, pm.product_id, p.category_id, c.name AS category_name, p.description, img.file_id "
            + "FROM promotion AS pm, product AS p, category AS c, product_image AS img "
            + "WHERE pm.product_id = p.id AND p.category_id = c.id AND "
            + "p.id = img.product_id AND img.type=\"ma\"";
}
