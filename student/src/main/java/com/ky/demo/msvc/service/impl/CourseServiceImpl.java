package com.ky.demo.msvc.service.impl;

import com.ky.demo.msvc.dao.CourseDao;
import com.ky.demo.msvc.entity.CourseEntity;
import com.ky.demo.msvc.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    CourseDao courseDao;


    @Override
    public List<CourseEntity> findAll() {
        return courseDao.findAll();
    }

    @Override
    public void saveCourse(CourseEntity courseEntity) {
        courseDao.save(courseEntity);
    }

    @Override
    public CourseEntity findOne(String id) {
        return courseDao.getOne(id);
    }

    @Override
    public void delete(String id) {
        courseDao.deleteById(id);
    }
}
