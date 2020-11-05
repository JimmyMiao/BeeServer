package com.app.server.mapper;

import com.app.server.model.CourseList;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseListMapper {
    @Select("select * from `course_list`")
    List<CourseList> getAllCourses();
}
