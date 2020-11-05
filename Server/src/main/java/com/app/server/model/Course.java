package com.app.server.model;

public class Course {

    public String course_id;
    public String course_name;
    public String user_id;
    public String user_name;
    public int user_portrait;

    @Override
    public String toString() {
        return "CourseModel{" +
                "course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_portrait='" + user_portrait + '\'' +
                '}';
    }
}
