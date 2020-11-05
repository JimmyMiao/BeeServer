package com.app.server.controller;

import com.app.server.mapper.CourseListMapper;
import com.app.server.model.CourseList;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseListController {
    @Autowired
    private CourseListMapper courseListMapper;

    @RequestMapping("/courselist")
    public BaseResponse getAllCourses(){
        List<CourseList> courseList = courseListMapper.getAllCourses();
        System.out.println(courseList.size());
        return new DataResponse(courseList,"All Courses", ConstResponse.STATUS_OK);
    }
}

