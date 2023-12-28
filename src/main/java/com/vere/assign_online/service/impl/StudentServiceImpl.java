package com.vere.assign_online.service.impl;

import com.vere.assign_online.mapper.StudentMapper;
import com.vere.assign_online.model.Student;
import com.vere.assign_online.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:StudentServiceImpl
 * Package:com.vere.demo.service.impl
 * Description:
 *
 * @Date:2022/4/14 11:09
 * @Author:3046990030@qq.com
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentByAccountId(Integer id) {
        return studentMapper.selectByAccountId(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.queryAllStudents();
    }

    @Override
    public Student checkStudentInfo(Long studentId, String name) {
        return studentMapper.queryStudent(studentId, name);
    }

    @Override
    public boolean modifyStudentInfo(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student) == 1;
    }
}
