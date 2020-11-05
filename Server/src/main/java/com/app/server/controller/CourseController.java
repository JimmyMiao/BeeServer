package com.app.server.controller;

import com.app.server.mapper.CourseMapper;
import com.app.server.mapper.UserMappper;
import com.app.server.model.Course;
import com.app.server.model.UserModel;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMappper userMappper;

    @RequestMapping("/course")
    public BaseResponse getCourses(String user_id){
        System.out.println("user_id="+user_id);

        List<Course> courses = courseMapper.findCourses(user_id);
        System.out.println(courses.size());
        return new DataResponse(courses,"Course list", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/course/member")
    public BaseResponse getCourseMembers(String course_id){
        System.out.println("course_id="+course_id);

        List<Course> members = courseMapper.getCourseMembers(course_id);
        System.out.println(members.size());
        return new DataResponse(members,"Course member list", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/course/information")
    public BaseResponse getMembersInformation(String course_id){
        System.out.println("course_id="+course_id);

        List<Course> members = courseMapper.getCourseMembers(course_id);
        List<UserModel> users = new ArrayList<>();
        for (Course member:members) {
            String user_id = member.user_id;
            users.add(userMappper.findUserById(user_id));
        }
        //System.out.println(members.size());
        return new DataResponse(users,"All students in this course", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/course/add")
    public BaseResponse addToCourse(String course_id, String course_name, String user_id){

        UserModel user = userMappper.findUserById(user_id);
        courseMapper.addToCourse(course_id, course_name, user_id, user.name, user.portrait);
        return new BaseResponse("Add to course success!", ConstResponse.STATUS_OK);
    }
}
