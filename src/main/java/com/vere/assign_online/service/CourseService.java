package com.vere.assign_online.service;

import com.vere.assign_online.common.exception.BadStudentInfoException;
import com.vere.assign_online.model.Course;
import com.vere.assign_online.model.Student;

import java.util.List;
import java.util.Map;

/**
 * ClassName:CourseService
 * Package:com.vere.assign_online.service
 * Description:
 *
 * @Date:2022/4/29 9:29
 * @Author:3046990030@qq.com
 */
public interface CourseService {
    List<Map<String, Object>> getCourseByTeacherId(Integer teacherId);
    List<Map<String, Object>> getCourseByStudentId(Integer studentId);
    List<Student> getStudents(Integer courseId);

    Course getCourseById(Integer id);

    boolean deleteStudent(Integer courseId, Integer studentId);
    boolean addStudent2Course(Integer courseId, Long studentId, String name) throws BadStudentInfoException;
}
