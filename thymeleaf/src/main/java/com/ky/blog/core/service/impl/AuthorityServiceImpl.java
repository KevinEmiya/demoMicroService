package com.ky.blog.core.service.impl;

import com.ky.blog.core.dao.AuthorityDao;
import com.ky.blog.core.entity.Authority;
import com.ky.blog.core.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityDao authorityDao;

    @Override
    public Optional<Authority> getAuthorityById(Long id) {
        return authorityDao.findById(id);
    }
}
