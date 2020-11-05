package com.app.server.model;

public class FriendModel {
    public String user_id;
    public String friend_id;
    public String friend_name;
    public int friend_portrait;

    @Override
    public String toString() {
        return "FriendModel{" +
                "user_id='" + user_id + '\'' +
                ", friend_id='" + friend_id + '\'' +
                ", friend_name='" + friend_name + '\'' +
                ", friend_portrait='" + friend_portrait + '\'' +
                '}';
    }
}
