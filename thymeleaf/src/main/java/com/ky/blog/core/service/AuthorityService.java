package com.ky.blog.core.service;

import com.ky.blog.core.entity.Authority;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AuthorityService {

    Optional<Authority> getAuthorityById(Long id);
}
