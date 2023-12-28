package com.vere.assign_online.service.impl;

import com.vere.assign_online.common.exception.BadStudentInfoException;
import com.vere.assign_online.mapper.CourseMapper;
import com.vere.assign_online.mapper.CourseStudentMapper;
import com.vere.assign_online.model.Course;
import com.vere.assign_online.model.Student;
import com.vere.assign_online.service.CourseService;
import com.vere.assign_online.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:CourseServiceImpl
 * Package:com.vere.assign_online.service.impl
 * Description:
 *
 * @Date:2022/4/29 9:43
 * @Author:3046990030@qq.com
 */

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseStudentMapper courseStudentMapper;

    @Autowired
    StudentService studentService;

    @Override
    public List<Map<String, Object>> getCourseByTeacherId(Integer teacherId) {
        return courseMapper.queryByTeacherId(teacherId);
    }

    @Override
    public List<Map<String, Object>> getCourseByStudentId(Integer studentId) {
        return courseMapper.queryByStudentId(studentId);
    }

    @Override
    public List<Student> getStudents(Integer courseId) {
        return courseStudentMapper.queryStudentsByCourseId(courseId);
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteStudent(Integer courseId, Integer studentId) {
        return courseStudentMapper.deleteByCourseIdAndStudentId(courseId, studentId);
    }

    @Override
    public boolean addStudent2Course(Integer courseId, Long studentId, String name) throws BadStudentInfoException {
        Student student = studentService.checkStudentInfo(studentId, name);
        if (student != null) {
            return courseStudentMapper.addStudent2Course(courseId, student.getId());
        } else {
            throw new BadStudentInfoException("学生信息错误");
        }
    }
}
