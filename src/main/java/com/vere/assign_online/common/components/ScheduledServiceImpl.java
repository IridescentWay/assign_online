package com.vere.assign_online.common.components;

import com.vere.assign_online.model.Course;
import com.vere.assign_online.model.Homework;
import com.vere.assign_online.model.Message;
import com.vere.assign_online.model.Student;
import com.vere.assign_online.service.CourseService;
import com.vere.assign_online.service.HomeworkService;
import com.vere.assign_online.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:ScheduledSeviceImpl
 * Package:com.vere.assign_online.service.impl
 * Description:
 *
 * @Date:2022/5/12 10:47
 * @Author:3046990030@qq.com
 */

@Component
public class ScheduledServiceImpl {
    @Autowired
    HomeworkService homeworkService;

    @Autowired
    CourseService courseService;

    @Autowired
    MessageService messageService;

    // 每天上午八点执行 @Scheduled(cron = "0 0 8 * * ?")
    // 每 10 秒执行一次@Scheduled(cron = "0/10 * * * * ?")
    @Scheduled(cron = "0 0 8 * * ?")
    public void noticeDyingHomework() {
        System.out.println("定时催交 ·······················");
        List<Homework> dyingHomework = homeworkService.getDyingHomework();
        for (Homework homework : dyingHomework) {
            Integer courseId = homework.getCourseId();
            Course course = courseService.getCourseById(courseId);
            List<Student> students = courseService.getStudents(courseId);
            for (Student student : students) {
                Message newMessage = new Message();
                newMessage.setFrom(course.getName());
                newMessage.setContent(String.format("作业 %s 即将结束，请尽快提交！", homework.getName()));
                newMessage.setTitle("作业提醒");
                newMessage.setTo(student.getName());
                newMessage.setType(Message.NOTIFICATION);
                messageService.addMessage(newMessage);
            }
        }
    }
}
