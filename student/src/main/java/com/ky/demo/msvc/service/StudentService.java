package com.ky.demo.msvc.service;

import com.ky.demo.msvc.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();

    public void saveStudent(Student student);

    public Student findOne(String id);

    public void delete(String id);

    public List<Student> findByName(String name);

    public void addCourse(String studentId, String courseId);
}
