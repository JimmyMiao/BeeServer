package com.app.server.mapper;

import com.app.server.model.FriendModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FriendMapper {

    @Select("select * from user_friend where user_id = #{user_id}")
    List<FriendModel> findFriends(@Param("user_id")String user_id);

    @Select("select * from user_friend where user_id = #{user_id} and friend_id = #{friend_id}")
    List<FriendModel> findPair(@Param("user_id")String user_id, @Param("friend_id")String friend_id);

    @Insert("insert into user_friend(user_id,friend_id,friend_name,friend_portrait) values(#{user_id},#{friend_id},#{friend_name},#{friend_portrait})")
    void addNewFriend(@Param("user_id")String user_id, @Param("friend_id")String friend_id, @Param("friend_name")String friend_name
            , @Param("friend_portrait")int friend_portrait);


    @Update("update user_friend set friend_name=#{new_name}, friend_portrait=#{new_portrait} where friend_id = #{user_id}")
    void updateAccount(@Param("user_id")String user_id, @Param("new_name")String new_name,
                                   @Param("new_portrait")int new_portrait);


}
