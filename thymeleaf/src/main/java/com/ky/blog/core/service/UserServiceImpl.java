package com.ky.blog.core.service;

import com.ky.blog.core.dao.UserDao;
import com.ky.blog.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    @Override
    public User saveOrUpdateUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        name = "%" + name + "%";
        Page<User> users = userDao.findByNameLike(name, pageable);
        return users;
    }
}
