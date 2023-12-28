package com.vere.assign_online.service;

import com.vere.assign_online.model.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName:HomeworkService
 * Package:com.vere.assign_online.service
 * Description:
 *
 * @Date:2022/4/28 11:49
 * @Author:3046990030@qq.com
 */
public interface HomeworkService {
    String POSTED = "已发布";
    String CREATED = "已创建";
    String COMMITTED = "已提交";
    String UNCOMMITTED = "未提交";
    List<Map<String, Object>> getHomeworkForTeacher(Integer courseId);
    List<Map<String, Object>> getHomeworkForStudent(Integer courseId);
    List<Map<String, Object>> getTasksByHomeworkId(Integer homeworkId);
    List<Student> getFilteredStudents(Integer homeworkId, String state);
    List<Homework> getDyingHomework();
    Homework getHomework(Integer id);
    boolean deleteHomework(Integer homeworkId);
    boolean addHomework(Homework homework);
    boolean modifyHomework(Homework homework);
    boolean updateHomeworkStatus(Integer homeworkId, String status);

    Task findTask(Integer id);
    boolean insertTask(Task task);
    int updateTask(Task task);
    int deleteTasks(Integer homeworkId);

    boolean addGuideline(HomeworkGuideline homeworkGuideline);
    List<String> getGuideline(Integer homeworkId);

    boolean addHomeworkFile(HomeworkFile homeworkFile);
    List<String> getHomeworkFile(Integer studentId, Integer homeworkId);
}
