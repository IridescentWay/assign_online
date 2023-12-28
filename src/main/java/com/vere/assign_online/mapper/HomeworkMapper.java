package com.vere.assign_online.mapper;

import com.vere.assign_online.model.Homework;
import com.vere.assign_online.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HomeworkMapper {
    @Select("select * from homework where course_id=#{courseId}")
    @ResultType(Map.class)
    List<Map<String, Object>> queryByCourseId(@Param("courseId") Integer courseId);

    @Select("select * from homework where course_id=#{courseId} and posted='已发布'")
    @ResultType(Map.class)
    List<Map<String, Object>> queryByCourseIdForStudent(@Param("courseId") Integer courseId);

    @Select("select s.* from student s, homework_file hf " +
            "where s.id=hf.student_id and homework_id=#{homeworkId} group by id")
    List<Student> queryStudentsCommitted(@Param("homeworkId") Integer homeworkId);

    @Select("select s.* from student s " +
            "left join (select * from homework_file where homework_id=#{homeworkId}) hf " +
            "on s.id=hf.student_id " +
            "where hf.student_id is null")
    List<Student> queryStudentsUncommitted(@Param("homeworkId") Integer homeworkId);

    @Select("select * from homework where posted='已发布' and date_add(now(), interval 2 day) > deadline")
    List<Homework> queryDyingHomework();

    @Update("update homework set posted=#{status} where id=#{homeworkId}")
    @ResultType(Boolean.class)
    boolean updateHomeworkStatus(@Param("homeworkId") Integer homeworkId, @Param("status") String status);

    @Update("update homework set committed=committed+1 where id=#{homeworkId}")
    int increaseCommitted(@Param("homeworkId") Integer homeworkId);

    int deleteByPrimaryKey(Integer id);

    int insert(Homework record);

    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertSelective(Homework record);

    Homework selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKey(Homework record);
}