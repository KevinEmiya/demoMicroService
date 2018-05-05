package com.ky.demo.msvc.dao;

import com.ky.demo.msvc.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<StudentEntity, String> {

    List<StudentEntity> findByName(String name);
}
