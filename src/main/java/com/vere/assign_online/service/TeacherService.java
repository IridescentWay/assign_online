package com.vere.assign_online.service;


import com.vere.assign_online.model.Teacher;

import java.util.List;

/**
 * ClassName:TeacherService
 * Package:com.vere.demo.service
 * Description:
 *
 * @Date:2022/4/19 18:42
 * @Author:3046990030@qq.com
 */

public interface TeacherService {
    Teacher getTeacherByAccountId(Integer accountId);
    List<Teacher> getAllTeachers();
}
