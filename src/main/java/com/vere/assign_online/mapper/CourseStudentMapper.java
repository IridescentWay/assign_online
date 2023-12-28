package com.vere.assign_online.mapper;

import com.vere.assign_online.model.CourseStudent;
import com.vere.assign_online.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentMapper {
    @Select("select * from student s, course_student cs where cs.course_id=#{courseId} and s.id=cs.student_id")
    @ResultType(com.vere.assign_online.model.Student.class)
    List<Student> queryStudentsByCourseId(@Param("courseId") Integer courseId);

    @Delete("delete from course_student where course_id=#{courseId} and student_id=#{studentId}")
    @ResultType(Boolean.class)
    boolean deleteByCourseIdAndStudentId(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    @Insert("insert into course_student(course_id, student_id) value(#{courseId}, #{studentId})")
    @ResultType(Boolean.class)
    boolean addStudent2Course(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseStudent record);

    int insertSelective(CourseStudent record);

    CourseStudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseStudent record);

    int updateByPrimaryKey(CourseStudent record);
}