package com.ky.thymeleaf.service;

import com.ky.thymeleaf.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    User saveOrUpdateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> listUser();
}
