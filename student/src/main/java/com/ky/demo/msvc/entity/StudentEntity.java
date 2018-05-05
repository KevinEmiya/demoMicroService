package com.ky.demo.msvc.entity;

import com.ky.demo.msvc.model.Student;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "stu")
@Table(name = "t_student")
@Data
public class StudentEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Student.Gender gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ScoreEntity> scores = new ArrayList<>();

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", scores=" + scores +
                '}';
    }
}
