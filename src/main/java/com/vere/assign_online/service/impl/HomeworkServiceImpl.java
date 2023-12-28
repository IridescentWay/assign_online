package com.vere.assign_online.service.impl;

import com.vere.assign_online.mapper.HomeworkFileMapper;
import com.vere.assign_online.mapper.HomeworkGuidelineMapper;
import com.vere.assign_online.mapper.HomeworkMapper;
import com.vere.assign_online.mapper.TaskMapper;
import com.vere.assign_online.model.*;
import com.vere.assign_online.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:HomeworkServiceImpl
 * Package:com.vere.assign_online.service.impl
 * Description:
 *
 * @Date:2022/4/28 14:39
 * @Author:3046990030@qq.com
 */

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    HomeworkFileMapper homeworkFileMapper;

    @Autowired
    HomeworkGuidelineMapper homeworkGuidelineMapper;


    @Override
    public List<Map<String, Object>> getHomeworkForTeacher(Integer courseId) {
        List<Map<String, Object>> ans = homeworkMapper.queryByCourseId(courseId);
        System.out.println(ans);
        return ans;
    }

    @Override
    public List<Map<String, Object>> getHomeworkForStudent(Integer courseId) {
        return homeworkMapper.queryByCourseIdForStudent(courseId);
    }

    @Override
    public List<Map<String, Object>> getTasksByHomeworkId(Integer homeworkId) {
        return taskMapper.queryByHomeworkId(homeworkId);
    }

    @Override
    public List<Student> getFilteredStudents(Integer homeworkId, String state) {
        return state.equals(HomeworkService.COMMITTED)
                ? homeworkMapper.queryStudentsCommitted(homeworkId)
                : homeworkMapper.queryStudentsUncommitted(homeworkId);
    }

    @Override
    public List<Homework> getDyingHomework() {
        return homeworkMapper.queryDyingHomework();
    }

    @Override
    public Homework getHomework(Integer id) {
        return homeworkMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteHomework(Integer homeworkId) {
        return homeworkMapper.deleteByPrimaryKey(homeworkId) == 1;
    }

    @Override
    public boolean addHomework(Homework homework) {
        return homeworkMapper.insertSelective(homework) == 1;
    }

    @Override
    public boolean modifyHomework(Homework homework) {
        return homeworkMapper.updateByPrimaryKeySelective(homework) == 1;
    }

    @Override
    public boolean updateHomeworkStatus(Integer homeworkId, String status) {
        return homeworkMapper.updateHomeworkStatus(homeworkId, status);
    }

    @Override
    public Task findTask(Integer id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insertTask(Task task) {
        return taskMapper.insertSelective(task) == 1;
    }

    @Override
    public int updateTask(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public int deleteTasks(Integer homeworkId) {
        return taskMapper.deleteByHomeworkId(homeworkId);
    }

    @Override
    public boolean addGuideline(HomeworkGuideline homeworkGuideline) {
        return homeworkGuidelineMapper.insertSelective(homeworkGuideline) == 1;
    }

    @Override
    public List<String> getGuideline(Integer homeworkId) {
        return homeworkGuidelineMapper.queryGuideline(homeworkId);
    }

    @Override
    public boolean addHomeworkFile(HomeworkFile homeworkFile) {
		boolean result = false;
        boolean committed = homeworkFileMapper.searchStudent(homeworkFile.getStudentId(), homeworkFile.getHomeworkId());
        if (!committed) {
            result = homeworkFileMapper.insertSelective(homeworkFile) == 1;
            homeworkMapper.increaseCommitted(homeworkFile.getHomeworkId());
        } else {
            result = homeworkFileMapper.insertSelective(homeworkFile) == 1;
        }
        return result;
    }

    @Override
    public List<String> getHomeworkFile(Integer studentId, Integer homeworkId) {
        return homeworkFileMapper.queryPersonalFile(studentId, homeworkId);
    }
}
