package com.ky.demo.msvc.controller;

import com.ky.demo.msvc.entity.CourseEntity;
import com.ky.demo.msvc.model.Adder;
import com.ky.demo.msvc.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course")
@Api("课程接口")
public class CourseController {

    @Resource
    private CourseService courseService;

    @ApiOperation(value = "创建一个课程")
    @PostMapping("")
    void addCourse(@RequestBody CourseEntity course){
        courseService.saveCourse(course);
    }

    @ApiOperation(value = "查询全部课程信息")
    @GetMapping("/all")
    @ResponseBody
    List<CourseEntity> getAllCourse(){
        return courseService.findAll();
    }

    @ApiOperation(value = "测试接口")
    @PostMapping("/add")
    @ResponseBody
    int testAdd(@RequestBody Adder adder){
        System.out.println("invoked!");
        return adder.getAdder() + adder.getAddend();
    }
}
