package com.kimi;

import com.kimi.entity.Course;
import com.kimi.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest(classes = ShardingJDBCApplication.class)
public class ShardingJDBCApplicationTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void main(){

        for (int i = 0; i < 20; i++) {
            Course course = new Course();
            course.setName("数据结构");
            course.setUserId(Math.abs(new Random().nextLong()%100));
            course.setStatus("存货");
            courseRepository.save(course);
        }
    }

    @Test
    public void find(){
        System.out.println(courseRepository.findById(630417154440691713L).orElseGet(Course::new).getUserId());

    }
}
