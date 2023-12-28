package com.vere.assign_online.controller;

import com.vere.assign_online.model.HomeworkFile;
import com.vere.assign_online.model.Message;
import com.vere.assign_online.model.Student;
import com.vere.assign_online.service.CourseService;
import com.vere.assign_online.service.HomeworkService;
import com.vere.assign_online.service.MessageService;
import com.vere.assign_online.service.StudentService;
import com.vere.assign_online.utils.DataFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName:StudentController
 * Package:com.vere.demo.controller
 * Description:
 *
 * @Date:2022/4/14 11:02
 * @Author:3046990030@qq.com
 */

@RestController
@RequestMapping(value = "student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    HomeworkService homeworkService;

    @GetMapping("/getClasses")
    @ResponseBody
    List<Map<String, Object>> getClassesByStudentId(@RequestParam("id") Integer studentId) {
        return DataFormatUtil.formatHumpNameForList(courseService.getCourseByStudentId(studentId));
    }

    @GetMapping("/getGuideline")
    @ResponseBody
    List<String> getGuideline(@RequestParam Integer homeworkId) {
        return homeworkService.getGuideline(homeworkId);
    }

    @PostMapping("/uploadHomework")
    @ResponseBody
    boolean uploadHomework(@RequestBody HomeworkFile homeworkFile) {
        return homeworkService.addHomeworkFile(homeworkFile);
    }
}
