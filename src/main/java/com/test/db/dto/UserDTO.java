//package com.test.db.dto;
//
//import com.test.db.dao.IDAO;
//import com.test.db.dao.UserDAO;
//import com.test.db.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import java.util.List;
//
//public class UserDTO implements IDTO<User> {
//    @Autowired
//    @Qualifier("userDAO")
//    private IDAO<User> userDAO;
//
//    @Override
//    public void add(User entity) {
//        userDAO.add(entity);
//    }
//
//    @Override
//    public void update(User entity) {
//        userDAO.update(entity);
//    }
//
//    @Override
//    public void delete(User entity) {
//        userDAO.delete(entity);
//    }
//
//    @Override
//    public User getOnId(Long id) {
//        return userDAO.getOnId(id);
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userDAO.findAll();
//    }
//
//    @Override
//    public void deleteOnId(Long id) {
//        userDAO.deleteOnId(id);
//    }
//    public User findByUserName(String username){
//        return ((UserDAO) userDAO).findByUserName(username);
//    }
//}
//
