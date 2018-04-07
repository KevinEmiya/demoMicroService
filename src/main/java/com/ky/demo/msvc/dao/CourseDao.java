package com.ky.demo.msvc.dao;

import com.ky.demo.msvc.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<CourseEntity, String> {
}
