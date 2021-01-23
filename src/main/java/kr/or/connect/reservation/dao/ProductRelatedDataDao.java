package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ProductRelatedDataDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.ProductImage;

import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRelatedDataDao {

    private NamedParameterJdbcTemplate jdbc;

    public ProductRelatedDataDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ProductImage> selectProductImages(Long productId) {
        Map<String, Long> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.query(SELECT_PRODUCT_IMAGES, params,
            BeanPropertyRowMapper.newInstance(ProductImage.class));
    }

    public List<DisplayInfoImage> selectDisplayInfoImages(Long displayId) {
        Map<String, Long> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.query(SELECT_DISPLAYINFO_IMAGES, params,
            BeanPropertyRowMapper.newInstance(DisplayInfoImage.class));
    }

    public Integer selectAverageScore(Long productId) {
        Map<String, Long> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.queryForObject(SELECT_AVERAGE_SCORE, params, Integer.class);
    }

    public List<ProductPrice> selectProductPrices(Long productId) {
        Map<String, Long> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.query(SELECT_PRODUCT_PRICES, params,
            BeanPropertyRowMapper.newInstance(ProductPrice.class));
    }

    public List<ReservationUserComment> selectReservationUserComments(Long productId,
        Integer start, Integer limit) {
        Map<String, Integer> params = new HashMap<>();
        params.put("productId", productId.intValue());
        params.put("start", start);
        params.put("limit", limit);
        return jdbc.query(SELECT_RESERVATION_USER_COMMENTS, params,
            BeanPropertyRowMapper.newInstance(ReservationUserComment.class));
    }

    public List<ReservationUserCommentImage> selectReservationUserCommentImages(Long commentId) {
        Map<String, Long> params = new HashMap<>();
        params.put("commentId", commentId);
        return jdbc.query(SELECT_RESERVATION_USER_COMMENTS_IMAGES, params,
            BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class));
    }

    public Integer selectReservationUserCommentsCount(Long productId) {
        Map<String, Long> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.queryForObject(SELECT_RESERVATION_USER_COMMENTS_COUNT, params, Integer.class);
    }
}
