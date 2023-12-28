package com.vere.assign_online.mapper;

import com.vere.assign_online.model.Course;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseMapper {
    @Select("select c.*, t.name teacher_name, t.faculty faculty " +
            "from course c, teacher t " +
            "where c.teacher_id=#{teacherId} and c.teacher_id=t.id")
    @ResultType(Map.class)
    List<Map<String, Object>> queryByTeacherId(@Param("teacherId") Integer teacherId);

    @Select("select qc.*, t.name teacher_name, t.faculty faculty " +
            "from (select c.* " +
            "from course c, course_student cs " +
            "where cs.student_id=#{studentId} and c.id=cs.course_id) qc, teacher t " +
            "where qc.teacher_id=t.id")
    @ResultType(Map.class)
    List<Map<String, Object>> queryByStudentId(@Param("studentId") Integer studentId);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}