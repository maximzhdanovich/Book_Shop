//package com.test.db.dto;
//
//import com.test.db.dao.IDAO;
//import com.test.db.model.Category;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import java.util.List;
//
//public class CategoryDTO implements IDTO<Category> {
//    @Autowired
//    @Qualifier("categoryDAO")
//    private IDAO<Category> categoryDAO;
//
//    @Override
//    public void add(Category entity) {
//        categoryDAO.add(entity);
//    }
//
//    @Override
//    public void update(Category entity) {
//        categoryDAO.update(entity);
//    }
//
//    @Override
//    public void delete(Category entity) {
//        categoryDAO.delete(entity);
//    }
//
//    @Override
//    public Category getOnId(Long id) {
//        return categoryDAO.getOnId(id);
//    }
//
//    @Override
//    public List<Category> findAll() {
//        return categoryDAO.findAll();
//    }
//
//    @Override
//    public void deleteOnId(Long id) {
//        categoryDAO.deleteOnId(id);
//    }
//}
