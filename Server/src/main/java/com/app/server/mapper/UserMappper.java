package com.app.server.mapper;

import com.app.server.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMappper {


    @Select("select * from user where user_id = #{user_id}")
    UserModel findUserById(@Param("user_id")String user_id);

    @Select("select * from user where email = #{email}")
    UserModel findUserByEmail(@Param("email")String email);

    @Update("update user set name=#{new_name}, password=#{new_password}, portrait=#{new_portrait} where user_id = #{user_id}")
    void updateAccountWithPassword(@Param("user_id")String user_id, @Param("new_name")String new_name,
                                   @Param("new_password")String new_password,@Param("new_portrait")int new_portrait);

    @Update("update user set name=#{new_name}, portrait=#{new_portrait} where user_id = #{user_id}")
    void updateAccountWithoutPassword(@Param("user_id")String user_id, @Param("new_name")String new_name,
                                   @Param("new_portrait")int new_portrait);

    @Update("update user set resume=#{new_resume} where user_id = #{user_id}")
    void updateResume(@Param("user_id")String user_id, @Param("new_resume")String new_resume);


    @Update("update user set c=#{new_c}, java=#{new_java}, python=#{new_python}, android=#{new_android}, ios=#{new_ios}, " +
            "htcss=#{new_htcss}, js=#{new_js}, bootstrap=#{new_bootstrap}, ai=#{new_ai}, nlp=#{new_nlp}, blockchain=#{new_blockchain}, " +
            "datamining=#{new_datamining} where user_id = #{user_id}")
    void updateSkill(@Param("user_id")String user_id, @Param("new_c")int new_c, @Param("new_java")int new_java,
                      @Param("new_python")int new_python, @Param("new_android")int new_android, @Param("new_ios")int new_ios,
                      @Param("new_htcss")int new_htcss, @Param("new_js")int new_js, @Param("new_bootstrap")int new_bootstrap,
                      @Param("new_ai")int new_ai, @Param("new_nlp")int new_nlp, @Param("new_blockchain")int new_blockchain,
                      @Param("new_datamining")int new_datamining);


    @Insert("insert into user(user_id,name,email,password,token,portrait,c,java,python,htcss,js,android,ios,bootstrap,ai,nlp,blockchain,datamining,resume) " +
            "values(#{user_id},#{name},#{email},#{password},#{token},#{portrait},#{c},#{java},#{python},#{htcss},#{js},#{android}," +
            "#{ios},#{bootstrap},#{ai},#{nlp},#{blockchain},#{datamining},#{resume})")
    void register(@Param("user_id")String user_id, @Param("name")String name, @Param("email")String email,
                  @Param("password")String password, @Param("token")String token, @Param("portrait")int portrait,
                  @Param("c")int c, @Param("java")int java, @Param("python")int python, @Param("htcss")int htcss,
                  @Param("js")int js, @Param("android")int android, @Param("ios")int ios, @Param("bootstrap")int bootstrap,
                  @Param("ai")int ai, @Param("nlp")int nlp, @Param("blockchain")int blockchain, @Param("datamining")int datamining,
                  @Param("resume")String resume);




}
