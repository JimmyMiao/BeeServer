package com.app.server.controller;

import com.app.server.mapper.CourseMapper;
import com.app.server.mapper.FriendMapper;
import com.app.server.mapper.GroupMapper;
import com.app.server.mapper.UserMappper;
import com.app.server.model.UserModel;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.response.DataResponse;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    private String appKey = "p5tvi9dspqia4";
    private String secret = "HMJT40cT8chO8K";
    private RongCloud cloud = RongCloud.getInstance(appKey,secret);
    private User user = cloud.user;

    @Autowired
    private UserMappper userMappper;
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private GroupMapper groupMapper;

    @RequestMapping("/user/login")
    public BaseResponse login(String email, String password){
        System.out.println("email="+email+" password="+password);
        UserModel userModel= userMappper.findUserByEmail(email);
        if(userModel==null){
            System.out.println("111");
            return new DataResponse(null, "Invalid Email Address", ConstResponse.STATUS_KNOWN_ERROR);
        }
        //password = SafeUtil.shortMD5(password);
        if(!userModel.password.equals(password)){
            System.out.println("222");
            return new DataResponse(null, "Invalid Password", ConstResponse.STATUS_KNOWN_ERROR);
        }
        return new DataResponse(userModel,"Log In Success", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/user/register")
    public BaseResponse register(String name, String email, String password, int portrait, int c, int java,
                                 int python, int htcss, int js, int android, int ios, int bootstrap,
                                 int ai, int nlp, int blockchain, int datamining){
        System.out.println("name="+name+" password="+password);
        if(email==null||password==null || name==null || portrait==0){
            return new DataResponse(null, ConstResponse.DESC_PARAM, ConstResponse.STATUS_PARAM_ERROR);
        }

        /*String nameCheck=PatternUtil.isNickname(name);
        if (!TextUtils.isEmpty(nameCheck)) {
            return new BaseResponse(nameCheck, ConstResponse.STATUS_KNOWN_ERROR);
        }*/
        /*if (!PatternUtil.isPassword(password)) {
            return new BaseResponse("请输入6~18位密码", ConstResponse.STATUS_KNOWN_ERROR);
        }*/
        UserModel userfind = userMappper.findUserByEmail(email);
        if (userfind != null) {
            return new DataResponse(null,"用户已注册", ConstResponse.STATUS_KNOWN_ERROR);
        }
        /*String token = SafeUtil.MD5(name + "app" + password);
        password = SafeUtil.shortMD5(password);*/
        String user_id = UUID.randomUUID().toString();
        String token = addNewUser(user_id, name);
        if (!token.equals("")) {
            userMappper.register(user_id, name, email, password, token, portrait, c, java, python, htcss, js, android,
                    ios, bootstrap, ai, nlp, blockchain, datamining, "");
            return new DataResponse(userMappper.findUserById(user_id), ConstResponse.DESC_OK,ConstResponse.STATUS_OK);
        }
        else {
            return new DataResponse(null,"Error", ConstResponse.STATUS_KNOWN_ERROR);
        }

    }

    /*@RequestMapping("/user/register")
    public BaseResponse getUser(String name, String email, String password, int portrait){

    }*/

    @RequestMapping("/user/get")
    public BaseResponse findUserById(String user_id){
        System.out.println(user_id);
        UserModel user = userMappper.findUserById(user_id);
        System.out.println(user.toString());
        return new DataResponse(user, ConstResponse.DESC_OK,ConstResponse.STATUS_OK);
    }


    public String addNewUser(String id, String name) {

        io.rong.models.user.UserModel userModel = new io.rong.models.user.UserModel().setId(id).setName(name)
                .setPortrait("http://www.rongcloud.cn/images/logo.png");;
        try {
            TokenResult result = user.register(userModel);
            System.out.println("getToken: "+result.toString());
            //return true;
            return result.getToken();
        } catch (Exception e) {
            //return false;
            return "";
        }


    }

    @RequestMapping("/user/account")
    public BaseResponse updateAccount(String user_id, String new_name, String new_password, int new_portrait){
        System.out.println(user_id);
        if (new_password.equals("")) {
            userMappper.updateAccountWithoutPassword(user_id, new_name, new_portrait);
        }
        else {
            userMappper.updateAccountWithPassword(user_id, new_name, new_password, new_portrait);
        }
        friendMapper.updateAccount(user_id, new_name, new_portrait);
        groupMapper.updateAccount(user_id, new_name, new_portrait);
        courseMapper.updateAccount(user_id, new_name, new_portrait);

        return new BaseResponse(ConstResponse.DESC_OK,ConstResponse.STATUS_OK);
    }

    @RequestMapping("/user/resume")
    public BaseResponse updateResume(String user_id, String new_resume){
        System.out.println(user_id);
        userMappper.updateResume(user_id, new_resume);

        return new BaseResponse(ConstResponse.DESC_OK,ConstResponse.STATUS_OK);
    }

    @RequestMapping("/user/skill")
    public BaseResponse updateSkill(String user_id, int new_c, int new_java, int new_python, int new_android,
                                    int new_ios, int new_htcss, int new_js, int new_bootstrap, int new_ai,
                                    int new_nlp, int new_blockchain, int new_datamining) {
        System.out.println(user_id);
        userMappper.updateSkill(user_id, new_c, new_java, new_python, new_android, new_ios, new_htcss, new_js,
                new_bootstrap, new_ai, new_nlp, new_blockchain, new_datamining);

        return new BaseResponse(ConstResponse.DESC_OK,ConstResponse.STATUS_OK);
    }


}