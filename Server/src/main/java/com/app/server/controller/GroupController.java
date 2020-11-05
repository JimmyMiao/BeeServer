package com.app.server.controller;

import com.app.server.mapper.GroupMapper;
import com.app.server.mapper.UserMappper;
import com.app.server.model.Group;
import com.app.server.model.UserModel;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.response.DataResponse;
import io.rong.RongCloud;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GroupController {

    private static String appKey = "p5tvi9dspqia4";
    private static String secret = "HMJT40cT8chO8K";
    private static RongCloud cloud = RongCloud.getInstance(appKey,secret);
    private static io.rong.methods.group.Group group = cloud.group;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMappper userMappper;

    @RequestMapping("/group/create")
    public BaseResponse createGroup(String group_name, String user_id) {
        System.out.println("user_name="+group_name+"   user_id="+user_id);
        if (group_name==null || user_id==null || group_name.equals("") || user_id.equals("")) {
            return new DataResponse(null, ConstResponse.DESC_PARAM, ConstResponse.STATUS_PARAM_ERROR);
        }

        UserModel user = userMappper.findUserById(user_id);
        if (user==null) {
            return new DataResponse(null, ConstResponse.DESC_PARAM, ConstResponse.STATUS_PARAM_ERROR);
        }

        String group_id = UUID.randomUUID().toString();
        /*groupMapper.createGroup(group_id, group_name, user_id, user.name, user.portrait);
        Group g = new Group(group_id, group_name, user_id, user.name, user.portrait);
        List<Group> list = new ArrayList<>();
        list.add(g);
        return new DataResponse(list, ConstResponse.DESC_OK,ConstResponse.STATUS_OK);*/

        if (createAndJoinGroup(group_id, group_name, user_id)) {
            groupMapper.createGroup(group_id, group_name, user_id, user.name, user.portrait);
            Group g = new Group(group_id, group_name, user_id, user.name, user.portrait);
            List<Group> list = new ArrayList<>();
            list.add(g);
            return new DataResponse(list, ConstResponse.DESC_OK,ConstResponse.STATUS_OK);

        }
        else {
            return new DataResponse(null, ConstResponse.DESC_PARAM, ConstResponse.STATUS_PARAM_ERROR);
        }
    }

    public boolean createAndJoinGroup(String group_id, String group_name, String user_id) {

        GroupModel groupModel = new GroupModel().setId(group_id).setName(group_name)
                .setMembers(new GroupMember[]{new GroupMember().setId(user_id)});

        try {
            group.create(groupModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/group")
    public BaseResponse getGroups(String user_id){
        System.out.println("user_id="+user_id);

        List<Group> groups = groupMapper.findGroups(user_id);
        System.out.println(groups.size());
        return new DataResponse(groups,"Group list", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/group/member")
    public BaseResponse getGroupMembers(String group_id){
        System.out.println("group_id="+group_id);

        List<Group> members = groupMapper.getGroupMembers(group_id);
        System.out.println(members.size());
        return new DataResponse(members,"Group member list", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/group/add")
    public BaseResponse addToGroup(String group_id, String group_name, String user_id){
        GroupModel groupModel = new GroupModel().setId(group_id).setName(group_name)
                .setMembers(new GroupMember[]{new GroupMember().setId(user_id)});
        try {
            Result groupJoinResult = (Result) group.join(groupModel);
            if (groupJoinResult.code == 200) {
                UserModel user = userMappper.findUserById(user_id);
                groupMapper.addToGroup(group_id, group_name, user_id, user.name, user.portrait);
                return new BaseResponse("Add to group success!", ConstResponse.STATUS_OK);
            }
            return new BaseResponse("Add to group fail!", ConstResponse.STATUS_PARAM_ERROR);
        } catch (Exception e) {
            return new BaseResponse("Add to group fail!", ConstResponse.STATUS_PARAM_ERROR);
        }


    }

}
