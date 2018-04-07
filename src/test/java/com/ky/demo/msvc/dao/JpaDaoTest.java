package com.ky.demo.msvc.dao;

import com.ky.demo.msvc.entity.CourseEntity;
import com.ky.demo.msvc.entity.ScoreEntity;
import com.ky.demo.msvc.entity.StudentEntity;
import com.ky.demo.msvc.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;

    private static String cachedStuId;

    @Before
    @Transactional
    public void generateStudentInfo() {
        System.out.println("创建关联数据...");
        StudentEntity stuWang = new StudentEntity();
        stuWang.setName("王大力");
        stuWang.setGender(Student.Gender.MALE);

        CourseEntity crsMath = new CourseEntity();
        crsMath.setName("高数");
        crsMath.setType("理");

        CourseEntity crsHistory = new CourseEntity();
        crsHistory.setName("近代史");
        crsHistory.setType("文");

        ScoreEntity scoreMath = new ScoreEntity();
        scoreMath.setCourse(crsMath);
        scoreMath.setStudent(stuWang);
        scoreMath.setScore(65);

        ScoreEntity scoreHistory = new ScoreEntity();
        scoreHistory.setCourse(crsHistory);
        scoreHistory.setStudent(stuWang);
        scoreHistory.setScore(70);

        stuWang.getScores().add(scoreMath);
        stuWang.getScores().add(scoreHistory);

        System.out.println("关联数据创建完成！");

        courseDao.save(crsMath);
        courseDao.save(crsHistory);

        crsMath.getScores().add(scoreMath);
        crsHistory.getScores().add(scoreHistory);

        studentDao.save(stuWang);

        cachedStuId = stuWang.getId();
    }

    @Test
    @Transactional
    public void queryStudentInfo() {

        StudentEntity stuSaved = studentDao.getOne(cachedStuId);
        List<ScoreEntity> scores = stuSaved.getScores();

        Assert.assertEquals(2, scores.size());
        double averageScore = scores.stream().map(item -> item.getScore())
                .reduce((sum, score) -> sum + score).get() * 1.0
                / scores.size();
        Assert.assertEquals((65 + 70) / 2.0, averageScore, 1e-3);
        System.out.println(stuSaved.getName() + "的平均分是：" + averageScore);
    }

    @Test
    @Transactional
    public void removeRelation() {
        StudentEntity stuSaved = studentDao.getOne(cachedStuId);
        String courseTypeAllowed = "文";
        System.out.println("移除" + stuSaved.getName() + "参与的所有非" + courseTypeAllowed + "类课程...");
        List<ScoreEntity> scores = stuSaved.getScores();
        stuSaved.getScores().removeAll(scores.stream()
                .filter(score -> !score.getCourse().getType().equals(courseTypeAllowed))
                .collect(Collectors.toList()));
        studentDao.save(stuSaved);
        stuSaved = studentDao.getOne(cachedStuId);
        scores = stuSaved.getScores();
        Assert.assertEquals(1, scores.size());
        ScoreEntity score = scores.get(0);
        Assert.assertEquals("近代史", score.getCourse().getName());
        System.out.println("移除成功！");

        final String stuName = stuSaved.getName();
        System.out.println("移除参与近代史课程中名为" + stuName + "的学生...");
        CourseEntity course = score.getCourse();
        course.setScores(scores.stream().filter(item -> !item.getStudent().getName().equals(stuName))
                .collect(Collectors.toList()));
        courseDao.save(course);
        course = courseDao.getOne(course.getId());
        Assert.assertEquals(0, course.getScores().size());
        System.out.println("移除成功！");
    }

}
