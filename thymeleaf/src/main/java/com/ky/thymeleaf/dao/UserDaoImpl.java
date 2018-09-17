package com.ky.thymeleaf.dao;

import com.ky.thymeleaf.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserDaoImpl implements UserDao {

    private static AtomicLong m_idGenerator = new AtomicLong();

    Map<Long, User> m_userCache = new HashMap<>();

    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if(id == null)
        {
            id = m_idGenerator.incrementAndGet();
            user.setId(id);
        }
        m_userCache.put(id, user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        m_userCache.remove(id);
    }

    @Override
    public User getUserById(Long id) {
        return m_userCache.get(id);
    }

    @Override
    public List<User> listUser() {
        return new ArrayList<>(m_userCache.values());
    }
}
