package com.ky.blog.core.dao;

import com.ky.blog.core.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
}
