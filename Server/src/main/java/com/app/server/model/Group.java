package com.app.server.model;

public class Group {
    public String group_id;
    public String group_name;
    public String user_id;
    public String user_name;
    public int user_portrait;

    public Group(){

    }

    public Group(String group_id,String group_name,String user_id,String user_name,int user_portrait) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_portrait = user_portrait;
    }

    @Override
    public String toString() {
        return "GroupModel{" +
                "group_id='" + group_id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_portrait='" + user_portrait + '\'' +
                '}';
    }
}
