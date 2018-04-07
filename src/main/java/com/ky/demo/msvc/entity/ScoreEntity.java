package com.ky.demo.msvc.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity(name = "relation")
@Table(name = "t_relation")
public class ScoreEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "stu_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", score=" + score +
                ", student=" + student.getName() +
                ", course=" + course.getName() +
                '}';
    }
}
