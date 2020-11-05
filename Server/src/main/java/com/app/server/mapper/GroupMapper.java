package com.app.server.mapper;

import com.app.server.model.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GroupMapper {

    @Insert("insert into `group`(group_id,group_name,user_id,user_name,user_portrait) " +
            "values(#{group_id},#{group_name},#{user_id},#{user_name},#{user_portrait})")
    void createGroup(@Param("group_id")String group_id,
                            @Param("group_name")String group_name,
                            @Param("user_id")String user_id,
                            @Param("user_name")String user_name,
                            @Param("user_portrait")int user_portrait);

    @Select("select * from `group` where user_id = #{user_id}")
    List<Group> findGroups(@Param("user_id")String user_id);

    @Select("select * from `group` where group_id = #{group_id}")
    List<Group> getGroupMembers(@Param("group_id")String group_id);

    @Insert("insert into `group`(group_id,group_name,user_id,user_name,user_portrait) values(#{group_id},#{group_name},#{user_id},#{user_name},#{user_portrait})")
    void addToGroup(@Param("group_id")String group_id,@Param("group_name")String group_name,
                    @Param("user_id")String user_id,@Param("user_name")String user_name,
                    @Param("user_portrait")int user_portrait);

    @Update("update `group` set user_name=#{new_name}, user_portrait=#{new_portrait} where user_id = #{user_id}")
    void updateAccount(@Param("user_id")String user_id, @Param("new_name")String new_name,
                       @Param("new_portrait")int new_portrait);
}
