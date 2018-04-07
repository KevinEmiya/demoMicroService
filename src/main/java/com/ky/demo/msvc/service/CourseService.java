package com.ky.demo.msvc.service;

import com.ky.demo.msvc.entity.CourseEntity;

import java.util.List;

public interface CourseService {
    public List<CourseEntity> findAll();

    public void saveCourse(CourseEntity courseEntity);

    public CourseEntity findOne(String id);

    public void delete(String id);
}
