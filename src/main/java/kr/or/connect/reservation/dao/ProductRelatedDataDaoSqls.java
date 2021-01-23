package kr.or.connect.reservation.dao;

public class ProductRelatedDataDaoSqls {

    public static final String SELECT_PRODUCT_IMAGES =
        "SELECT img.product_id, img.id AS product_image_id, img.type, f.id AS file_info_id, "
            + "f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, "
            + "f.modify_date FROM product_image AS img, file_info AS f "
            + "WHERE img.file_id = f.id AND img.product_id = :productId AND img.type =\"ma\"";
    public static final String SELECT_DISPLAYINFO_IMAGES =
        "SELECT img.id, img.display_info_id, img.file_id, f.file_name, f.save_file_name, "
            + "f.content_type, f.delete_flag, f.create_date, f.modify_date "
            + "FROM display_info_image AS img, file_info AS f "
            + "WHERE img.file_id = f.id AND img.display_info_id = :displayId";
    public static final String SELECT_AVERAGE_SCORE =
        "SELECT IFNULL(AVG(score),0) FROM reservation_user_comment WHERE product_id = :productId";
    public static final String SELECT_PRODUCT_PRICES =
        "SELECT id, product_id, price_type_name, price, discount_rate, create_date, modify_date "
            + "FROM product_price where product_id = :productId ORDER BY id DESC";
    public static final String SELECT_RESERVATION_USER_COMMENTS =
        "SELECT r_comment.id, r_comment.product_id, r_comment.reservation_info_id, r_comment.score, "
            + "r.reservation_email, r_comment.comment, r_comment.create_date, r_comment.modify_date "
            + "FROM reservation_info AS r JOIN reservation_user_comment AS r_comment ON r.id = r_comment.reservation_info_id "
            + "WHERE r.product_id = :productId ORDER BY r.id DESC LIMIT :start, :limit";
    public static final String SELECT_RESERVATION_USER_COMMENTS_IMAGES =
        "SELECT r_img.id AS image_id, r_img.reservation_info_id, r_img.reservation_user_comment_id, r_img.file_id, f.file_name,"
            + " f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date "
            + "FROM reservation_user_comment AS r_comment LEFT OUTER JOIN reservation_user_comment_image AS r_img "
            + "ON r_comment.id = r_img.reservation_user_comment_id JOIN file_info AS f ON r_img.file_id = f.id WHERE r_comment.id = :commentId";
    public static final String SELECT_RESERVATION_USER_COMMENTS_COUNT =
        "SELECT COUNT(*) FROM reservation_user_comment as r WHERE r.product_id = :productId";
}
