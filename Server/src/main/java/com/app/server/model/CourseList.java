package com.app.server.model;

public class CourseList {
    public String course_id;
    public String course_name;

    @Override
    public String toString() {
        return "CourseListModel{" +
                "course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                '}';
    }
}
