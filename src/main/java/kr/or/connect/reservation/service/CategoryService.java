package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dto.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> getCategories() {
        return categoryDao.selectAllWithProductCount();
    }

    public Integer getCount() {
        return categoryDao.selectCount();
    }
}
