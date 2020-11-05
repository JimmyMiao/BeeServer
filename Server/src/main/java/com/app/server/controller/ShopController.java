package com.app.server.controller;

import com.app.server.mapper.ShopMapper;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.response.DataResponse;
import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    @Autowired
    private ShopMapper shopMapper;

    @RequestMapping("/home/shoplist/1")
    public BaseResponse shop1(){
        return new DataResponse(shopMapper.selectAll1(), ConstResponse.DESC_OK, ConstResponse.STATUS_OK);
    }

    @RequestMapping("/home/shoplist/2")
    public BaseResponse shop2(){
        //addNewUser("1","1");
        //createAndJoinGroup("1","1");
        //System.out.println(addNumberToGroup());
        return new DataResponse(shopMapper.selectAll2(), ConstResponse.DESC_OK, ConstResponse.STATUS_OK);
    }

    private String appKey = "p5tvi9dspqia4";
    private String secret = "HMJT40cT8chO8K";
    private RongCloud cloud = RongCloud.getInstance(appKey,secret);
    private User user = cloud.user;
    public boolean getToken() {
        return false;
    }
    public boolean addNewUser(String id, String name) {

        UserModel userModel = new UserModel().setId("1").setName("Jimmy")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");;
        try {
            TokenResult result = user.register(userModel);
            System.out.println("getToken: "+result.toString());
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public boolean createAndJoinGroup(String id, String name) {

        Group group = cloud.group;
        GroupModel groupModel = new GroupModel().setId("group1").setName("Group 1")
                .setMembers(new GroupMember[]{new GroupMember().setId("1")});

        try {
            group.create(groupModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addNumberToGroup() {
        GroupModel groupModel = new GroupModel().setId("group1").setName("Group 1")
                .setMembers(new GroupMember[]{new GroupMember().setId("2")});
        try {
            Result result = cloud.group.invite(groupModel);
            System.out.println(result.toString());
            return true;
        } catch (Exception e) {
            return false;
        }

    }






}
