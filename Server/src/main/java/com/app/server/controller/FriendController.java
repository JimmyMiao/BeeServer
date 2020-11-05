package com.app.server.controller;

import com.app.server.mapper.FriendMapper;
import com.app.server.mapper.UserMappper;
import com.app.server.model.FriendModel;
import com.app.server.model.UserModel;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.response.DataResponse;
import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.methods.message.system.MsgSystem;
import io.rong.methods.user.User;
import io.rong.models.message.SystemMessage;
import io.rong.models.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController {

    private String appKey = "p5tvi9dspqia4";
    private String secret = "HMJT40cT8chO8K";
    private RongCloud cloud = RongCloud.getInstance(appKey,secret);
    private User user = cloud.user;
    private MsgSystem system = cloud.message.system;

    @Autowired
    FriendMapper friendMapper;

    @Autowired
    UserMappper userMappper;

    @RequestMapping("/friend/list")
    public BaseResponse getFriends(String user_id){
        System.out.println("user_id="+user_id);

        List<FriendModel> friends = friendMapper.findFriends(user_id);
        System.out.println(friends.size());
        return new DataResponse(friends,"Friend list", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/friend/add")
    public BaseResponse addFriend(String user_id, String friend_id) throws Exception {
        System.out.println("user_id="+user_id+" "+"friend_id="+friend_id);

        List<FriendModel> pair = friendMapper.findPair(user_id, friend_id);
        if (pair != null && pair.size()!=0) {
            return new BaseResponse("Is friend", ConstResponse.STATUS_KNOWN_ERROR);
        }

        String[] targetId = {friend_id};

        TxtMessage txtMessage = new TxtMessage("add", "add");
        SystemMessage systemMessage = new SystemMessage()
                .setSenderId(user_id)
                .setTargetId(targetId)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setIsPersisted(0)
                .setIsCounted(0)
                .setContentAvailable(0);

        ResponseResult result = system.send(systemMessage);
        System.out.println(result.toString());
        return new BaseResponse("Add friend request", ConstResponse.STATUS_OK);

    }

    @RequestMapping("/friend/agree")
    public BaseResponse agreeAddFriend(String user_id, String friend_id) {

        UserModel user = userMappper.findUserById(user_id);
        UserModel friend = userMappper.findUserById(friend_id);
        if (user != null && friend != null) {
            friendMapper.addNewFriend(user.user_id, friend.user_id, friend.name, friend.portrait);
            friendMapper.addNewFriend(friend.user_id, user.user_id, user.name, user.portrait);
            return new BaseResponse("Add friend success!", ConstResponse.STATUS_OK);
        }
        return new BaseResponse("Add friend fail!", ConstResponse.STATUS_PARAM_ERROR);
    }

    /*@RequestMapping("/friend/disagree")
    public BaseResponse disAgreeAddFriend(String user_id, String friend_id) {

    }*/


}
