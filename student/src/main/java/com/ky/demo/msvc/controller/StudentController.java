package com.ky.demo.msvc.controller;

import com.ky.demo.msvc.model.Student;
import com.ky.demo.msvc.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
@Api("学生接口")
public class StudentController {

    @Resource
    StudentService service;

    @ApiOperation(value = "创建一个学生")
    @PostMapping("")
    void addStudent(@RequestBody Student student) {
        service.saveStudent(student);
    }

    @ApiOperation(value = "删除一个学生")
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "通过id查询学生")
    @GetMapping("/{id}")
    @ResponseBody
    Student findStudentById(@PathVariable String id) {
        return service.findOne(id);
    }

    @ApiOperation(value = "通过学生名查找学生")
    @GetMapping("/name/{name}")
    @ResponseBody
    List<Student> findStudentsByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @ApiOperation(value = "查询全部学生信息")
    @GetMapping("/all")
    @ResponseBody
    List<Student> queryAllStudent() {
        return service.findAll();
    }

}
