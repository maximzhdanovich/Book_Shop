//package com.test.db.dto;
//
//import com.test.db.dao.IDAO;
//import com.test.db.model.Author_Image;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import java.util.List;
//
//public class Author_ImageDTO implements IDTO<Author_Image> {
//    @Autowired
//    @Qualifier("author_imageDAO")
//    private IDAO<Author_Image> author_imageDAO;
//    @Override
//    public void add(Author_Image entity) {
//        author_imageDAO.add(entity);
//    }
//
//    @Override
//    public void update(Author_Image entity) {
//        author_imageDAO.update(entity);
//    }
//
//    @Override
//    public void delete(Author_Image entity) {
//        author_imageDAO.delete(entity);
//    }
//
//    @Override
//    public Author_Image getOnId(Long id) {
//        return author_imageDAO.getOnId(id);
//    }
//
//    @Override
//    public List<Author_Image> findAll() {
//        return author_imageDAO.findAll();
//    }
//
//    @Override
//    public void deleteOnId(Long id) {
//        author_imageDAO.deleteOnId(id);
//    }
//}
