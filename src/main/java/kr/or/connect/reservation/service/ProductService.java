package kr.or.connect.reservation.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.ProductRelatedDataDao;
import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private static final int PRODUCT_LIMIT = 4;
    private static final int COMMENT_LIMIT = 5;

    private final ProductDao productDao;
    private final UserDao userDao;

    private final ProductRelatedDataDao productRelatedDataDao;

    public List<Product> getProductsByCategory(Long categoryId, int start) {
        return productDao.selectAllByCategory(categoryId, start, PRODUCT_LIMIT);
    }

    public List<Product> getProducts(int start) {
        return productDao.selectAll(start, PRODUCT_LIMIT);
    }

    public int getCountByCategory(Long categoryId) {
        return productDao.selectCountByCategory(categoryId);
    }

    public int getCount() {
        return productDao.selectCount();
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

    public List<DisplayinfosComment> getReservationUserCommentsWithImagesByProductId(
            Long productId, int start) {
        List<ReservationUserComment> comments = productRelatedDataDao
                .selectReservationUserComments(productId, start, COMMENT_LIMIT);
        return comments.stream()
                .map(comment -> new DisplayinfosComment(comment, productRelatedDataDao.selectReservationUserCommentImages(comment.getId())
                        , userDao.getMemberById(comment.getUserId()).getEmail()))
                .collect(Collectors.toList());
    }

    public List<Comment> getCommentsWithImagesByProductId(
            Long productId, int start) {
        List<ReservationUserComment> comments = productRelatedDataDao
                .selectReservationUserComments(productId, start, COMMENT_LIMIT);
        return comments.stream()
                .map(comment -> new Comment(comment, productRelatedDataDao.selectReservationUserCommentImages(comment.getId())))
                .collect(Collectors.toList());
    }

    public int getReservationUserCommentsCountByProductId(Long productId) {
        return productRelatedDataDao.selectReservationUserCommentsCount(productId);
    }
}
