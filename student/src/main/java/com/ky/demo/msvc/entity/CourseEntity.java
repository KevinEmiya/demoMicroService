package com.ky.demo.msvc.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "course")
@Table(name = "t_course")
@Data
public class CourseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<ScoreEntity> scores = new ArrayList<>();

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }
}
