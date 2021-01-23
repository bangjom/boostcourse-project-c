package kr.or.connect.reservation.dao;

public class ProductDaoSqls {

    public static final String SELECT_PAGING =
        "SELECT p.id, c.id AS category_id, d.id AS display_info_id, c.name, "
            + "p.description, p.content, p.event, d.opening_hours, d.place_name, d.place_lot, "
            + "d.place_street, d.tel, d.homepage, d.email, d.create_date, d.modify_date, img.file_id "
            + "FROM category AS c, product AS p, display_info AS d, product_image AS img "
            + "WHERE c.id = :categoryId AND c.id = p.category_id AND p.id = d.product_id "
            + "AND p.id = img.product_id AND img.type=\"ma\" ORDER BY id limit :start, :limit";

    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM product JOIN display_info ON "
        + "product.id = display_info.product_id WHERE product.category_id= :categoryId";

    public static final String SELECT_BY_DISPLAY_ID =
        "SELECT p.id, c.id AS category_id, d.id AS display_info_id, c.name, "
            + "p.description, p.content, p.event, d.opening_hours, d.place_name, d.place_lot, "
            + "d.place_street, d.tel, d.homepage, d.email, d.create_date, d.modify_date, img.file_id "
            + "FROM category AS c, product AS p, display_info AS d, product_image AS img "
            + "WHERE d.id = :displayId AND c.id = p.category_id AND p.id = d.product_id "
            + "AND p.id = img.product_id AND img.type=\"ma\"";

    public static final String SELECT_ALL =
        "SELECT p.id, c.id AS category_id, d.id AS display_info_id, c.name, p.description, "
            + "p.content, p.event, d.opening_hours, d.place_name, d.place_lot, d.place_street, "
            + "d.tel, d.homepage, d.email, d.create_date, d.modify_date, img.file_id FROM "
            + "category AS c, product AS p, display_info AS d, product_image AS img "
            + "WHERE c.id = p.category_id AND p.id = d.product_id AND "
            + "p.id = img.product_id AND img.type=\"ma\" ORDER BY id limit :start, :limit";

    public static final String SELECT_ALL_COUNT = " SELECT COUNT(*) FROM product JOIN display_info "
        + "ON product.id = display_info.product_id";
}
