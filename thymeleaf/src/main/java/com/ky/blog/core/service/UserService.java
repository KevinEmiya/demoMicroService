package com.ky.blog.core.service;

import com.ky.blog.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserService {

    User saveOrUpdateUser(User user);

    User registerUser(User user);

    void removeUser(Long id);

    Optional<User> getUserById(Long id);

    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
