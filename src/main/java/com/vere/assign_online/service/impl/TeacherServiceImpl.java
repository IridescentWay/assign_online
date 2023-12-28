package com.vere.assign_online.service.impl;

import com.vere.assign_online.mapper.TeacherMapper;
import com.vere.assign_online.model.Teacher;
import com.vere.assign_online.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:TeacherServiceImpl
 * Package:com.vere.demo.service.impl
 * Description:
 *
 * @Date:2022/4/19 18:45
 * @Author:3046990030@qq.com
 */


@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherByAccountId(Integer accountId) {
        return teacherMapper.selectByAccountId(accountId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherMapper.queryAllTeachers();
    }
}
