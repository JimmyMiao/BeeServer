package com.app.server.mapper;

import com.app.server.model.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseMapper {

    @Select("select * from `course` where user_id = #{user_id}")
    List<Course> findCourses(@Param("user_id")String user_id);

    @Select("select * from `course` where course_id = #{course_id}")
    List<Course> getCourseMembers(@Param("course_id")String course_id);

    @Insert("insert into `course`(course_id,course_name,user_id,user_name,user_portrait) values(#{course_id},#{course_name},#{user_id},#{user_name},#{user_portrait})")
    void addToCourse(@Param("course_id")String course_id,@Param("course_name")String course_name,
                    @Param("user_id")String user_id,@Param("user_name")String user_name,
                    @Param("user_portrait")int user_portrait);

    @Update("update `course` set user_name=#{new_name}, user_portrait=#{new_portrait} where user_id = #{user_id}")
    void updateAccount(@Param("user_id")String user_id, @Param("new_name")String new_name,
                       @Param("new_portrait")int new_portrait);
}
