package com.vere.assign_online.service;


import com.vere.assign_online.model.Student;

import java.util.List;

/**
 * ClassName:StudentService
 * Package:com.vere.demo.service
 * Description:
 *
 * @Date:2022/4/14 11:07
 * @Author:3046990030@qq.com
 */

public interface StudentService {
    Student getStudentByAccountId(Integer accountId);
    List<Student> getAllStudents();
    Student checkStudentInfo(Long studentId, String name);
    boolean modifyStudentInfo(Student student);
}
