package com.kimi;

import com.kimi.entity.Course;
import com.kimi.entity.User;
import com.kimi.repository.CourseRepository;
import com.kimi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest(classes = ShardingJDBCApplication.class)
public class ShardingJDBCApplicationTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;


    /**
     * 水平划分
     */
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


    /**
     * 垂直划分
     */
    @Test
    public void vertical(){
        User user = new User();
        user.setStatus("激活");
        user.setUsername("郭富城");
        userRepository.save(user);
    }

    @Test
    public void findUser(){
        System.out.println(userRepository.findById(630447493783486464L).orElseGet(User::new).getUsername());
    }
}
