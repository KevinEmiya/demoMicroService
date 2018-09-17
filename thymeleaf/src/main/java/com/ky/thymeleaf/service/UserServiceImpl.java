package com.ky.thymeleaf.service;

import com.ky.thymeleaf.dao.UserDao;
import com.ky.thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User saveOrUpdateUser(User user) {
        return userDao.saveOrUpdateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }
}
