package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    @Autowired
    PromotionDao promotionDao;

    public List<Promotion> getPromotions() {
        return promotionDao.selectALl();
    }
}
