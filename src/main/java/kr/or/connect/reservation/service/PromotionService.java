package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionDao promotionDao;

    public List<Promotion> getPromotions() {
        return promotionDao.selectAll();
    }
}
