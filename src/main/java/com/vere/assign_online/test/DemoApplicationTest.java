package com.vere.assign_online.test;

import com.vere.assign_online.service.CourseService;
import com.vere.assign_online.service.HomeworkService;
import com.vere.assign_online.utils.QiNiuUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * ClassName:DemoApplicationTest
 * Package:com.vere.demo.test
 * Description:
 *
 * @Date:2022/4/13 17:19
 * @Author:3046990030@qq.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {
    @Resource
    CourseService courseService;

    @Resource
    HomeworkService homeworkService;

    @Test
    public void serviceTest() {
        System.out.println(courseService.getStudents(1));
    }

    @Resource
    QiNiuUtil qiNiuUtil;

    @Test
    public void qiNiuTest() {
        String baseUrl = "http://raw7uz81p.hd-bkt.clouddn.com/assign_online/db_assign_online.sql";
    }
}
