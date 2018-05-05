package com.ky.demo.msvc.model;

import com.ky.demo.msvc.entity.ScoreEntity;
import com.ky.demo.msvc.entity.StudentEntity;
import lombok.Data;

import java.util.List;

@Data
public class Student {

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    List<ScoreEntity> scores;

    public Student() {
    }

    public static Student fromEntity(StudentEntity entity)
    {
        Student student = new Student();
        student.setId(entity.getId());
        student.setName(entity.getName());
        student.setGender(entity.getGender());
        student.setScores(entity.getScores());
        return student;
    }

    public StudentEntity toEntity()
    {
        StudentEntity entity = new StudentEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setGender(gender);
        entity.setScores(scores);
        return entity;
    }

}
