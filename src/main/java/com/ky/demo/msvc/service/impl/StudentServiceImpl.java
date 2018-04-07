package com.ky.demo.msvc.service.impl;

import com.ky.demo.msvc.service.StudentService;
import com.ky.demo.msvc.dao.StudentDao;
import com.ky.demo.msvc.entity.StudentEntity;
import com.ky.demo.msvc.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentDao studentDao;

    @Override
    public List<Student> findAll() {
        List<StudentEntity> entities = studentDao.findAll();
        List<Student> students = entities.stream().collect(ArrayList::new, (list, item) -> {
            list.add(Student.fromEntity(item));
        }, ArrayList::addAll);
        return students;
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.save(student.toEntity());
    }

    @Override
    public Student findOne(String id) {
        StudentEntity entity = studentDao.getOne(id);
        return Student.fromEntity(entity);
    }

    @Override
    public void delete(String id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDao.findByName(name).stream().collect(ArrayList::new, (list, item) -> {
            list.add(Student.fromEntity(item));
        }, ArrayList::addAll);
    }

    @Override
    public void addCourse(String studentId, String courseId) {

    }
}
