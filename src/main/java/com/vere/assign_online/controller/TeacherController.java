package com.vere.assign_online.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vere.assign_online.common.exception.BadStudentInfoException;
import com.vere.assign_online.model.*;
import com.vere.assign_online.service.*;
import com.vere.assign_online.utils.DataFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName:TeacherController
 * Package:com.vere.assign_online.controller
 * Description:
 *
 * @Date:2022/4/28 16:41
 * @Author:3046990030@qq.com
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    CourseService courseService;

    @Autowired
    HomeworkService homeworkService;

    @Autowired
    StudentService studentService;

    @Autowired
    MessageService messageService;

    @GetMapping("/getClasses")
    @ResponseBody
    List<Map<String, Object>> getClassesByTeacherId(@RequestParam("id") Integer teacherId) {
        return DataFormatUtil.formatHumpNameForList(courseService.getCourseByTeacherId(teacherId));
    }

    @GetMapping("/getStudentsCommitted")
    @ResponseBody
    List<Student> getStudentsCommitted(@RequestParam Integer homeworkId) {
        return homeworkService.getFilteredStudents(homeworkId, HomeworkService.COMMITTED);
    }

    @GetMapping("/getStudentsUncommitted")
    @ResponseBody
    List<Student> getStudentsUncommitted(@RequestParam Integer homeworkId) {
        return homeworkService.getFilteredStudents(homeworkId, HomeworkService.UNCOMMITTED);
    }

    @PostMapping("/addHomework")
    boolean addHomework(@RequestBody Map<String, Object> map) {
        String jsonStr = JSON.toJSONString(map);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<Task> tasks = JSON.parseArray(jsonObject.getString("task"), Task.class);
        Homework homework = JSON.parseObject(jsonStr, Homework.class);
        homeworkService.addHomework(homework);
        Integer homeworkId = homework.getId();
        for (Task task : tasks) {
            task.setHomeworkId(homeworkId);
            homeworkService.insertTask(task);
        }
        return true;
    }

    @PostMapping("/addStudent")
    boolean addStudent(@RequestParam Integer courseId, @RequestParam Long studentId, @RequestParam String name) throws BadStudentInfoException { return courseService.addStudent2Course(courseId, studentId, name); }

    @PostMapping("/uploadGuideline")
    boolean uploadGuideline(@RequestBody  HomeworkGuideline homeworkGuideline) {
        return homeworkService.addGuideline(homeworkGuideline);
    }

    @PostMapping("/urgeStudent")
    boolean urgeStudent(@RequestParam String teacherName, @RequestParam String studentName, @RequestParam String homeworkName) {
        Message newMessage = new Message();
        newMessage.setFrom(teacherName);
        newMessage.setTo(studentName);
        newMessage.setType(Message.NOTIFICATION);
        newMessage.setTitle("作业提醒");
        newMessage.setContent(String.format("作业 %s 即将结束，请尽快提交！", homeworkName));
        return messageService.addMessage(newMessage) == 1;
    }

    @DeleteMapping("/deleteHomework")
    boolean deleteHomeworkById(@RequestParam Integer homeworkId) {
        // todo 作业指导书和学生们提交的作业不忙删除，可以设置在三个月如果不撤销删除就删除所有文件
        homeworkService.deleteTasks(homeworkId);
        return homeworkService.deleteHomework(homeworkId);
    }

    @DeleteMapping("/deleteStudent")
    boolean deleteStudentInCourse(@RequestParam Integer courseId, @RequestParam Integer studentId) {
        return courseService.deleteStudent(courseId, studentId);
    }

    @PutMapping("/modifyHomework")
    boolean modifyHomework(@RequestBody Map<String, Object> map) {
        String jsonStr = JSON.toJSONString(map);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<Task> tasks = JSON.parseArray(jsonObject.getString("task"), Task.class);
        Homework homework = JSON.parseObject(jsonStr, Homework.class);
		Integer homeworkId = homework.getId();
        homeworkService.deleteTasks(homeworkId);
        for (Task task : tasks) {
            task.setHomeworkId(homeworkId);
            if (homeworkService.findTask(task.getId()) != null) {
                homeworkService.updateTask(task);
            } else {
                homeworkService.insertTask(task);
            }
        }
		homework.setPosted(jsonObject.getBoolean("posted") ? HomeworkService.POSTED : HomeworkService.CREATED);
        return homeworkService.modifyHomework(homework);
    }

    @PutMapping("/modifyStudent")
    boolean modifyStudent(@RequestBody Student student) { return studentService.modifyStudentInfo(student); }

    @PutMapping("/postHomework")
    boolean postHomework(@RequestParam Integer homeworkId) {
        return homeworkService.updateHomeworkStatus(homeworkId, HomeworkService.POSTED);
    }
}
