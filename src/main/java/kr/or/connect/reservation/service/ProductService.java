package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.ProductRelatedDataDao;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationUserComment;

import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private static final int LIMIT = 4;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductRelatedDataDao productRelatedDataDao;

    public List<Product> getProductsByCategory(Long categoryId, int start) {
        return productDao.selectAllByCategory(categoryId, start, LIMIT);
    }

    public int getCountByCategory(Long categoryId) {
        return productDao.selectCountByCategory(categoryId);
    }

    public Product getProductByDisplayId(Long displayId) {
        return productDao.selectOneById(displayId);
    }

    public List<ProductImage> getProductImageByProductId(Long productId) {
        return productRelatedDataDao.selectProductImages(productId);
    }

    public List<DisplayInfoImage> getDisplayInfoImageByDisplayId(Long displayId) {
        return productRelatedDataDao.selectDisplayInfoImages(displayId);
    }

    public int getAverageScoreByProductId(Long productId) {
        return productRelatedDataDao.selectAverageScore(productId);
    }

    public List<ProductPrice> getProductPricesByProductId(Long productId) {
        return productRelatedDataDao.selectProductPrices(productId);
    }

    public List<ReservationUserComment> getReservationUserCommentsWithImagesByProductId(
        Long productId, int start) {
        List<ReservationUserComment> comments = productRelatedDataDao
            .selectReservationUserComments(productId, start, LIMIT);
        comments.stream()
            .forEach(comment -> comment.setReservationUserCommentImages(
                productRelatedDataDao.selectReservationUserCommentImages(comment.getId())));

        return comments;
    }

    public int getReservationUserCommentsCountByProductId(Long productId) {
        return productRelatedDataDao.selectReservationUserCommentsCount(productId);
    }
}
