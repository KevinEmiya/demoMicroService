package com.ky.thymeleaf.dao;

import com.ky.thymeleaf.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {

    User saveOrUpdateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> listUser();
}
