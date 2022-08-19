/**
 * Author: hyh
 * Date: 2022/8/12 13:39
 * Describe:
 */

package com.gfxd.wb.controller;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.entity.model.LoginUser;
import com.gfxd.wb.entity.model.User;
import com.gfxd.wb.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Author: hyh
 * Date: 2022/8/12 13:39
 * Describe:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public JSONObject login(@RequestBody LoginUser loginUser, HttpSession session) throws IOException {
        JSONObject jsonObject = userInfoService.login(loginUser,session);
        return jsonObject;
    }

    /**
     * 初始化用户列表页面
     */
    @PostMapping(value = "/initListUserPage")
    @ResponseBody
    public Map<String, Object> initListUserPage() {
        JSONObject jsonObject = userInfoService.initListUserPage();
        return jsonObject;
    }

    /**
     * 初始化编辑用户页面
     */
    @PostMapping(value = "/initEditUser")
    @ResponseBody
    public Map<String, Object> initEditUser() {
        JSONObject jsonObject = userInfoService.initEditUser();
        return jsonObject;
    }

    /**
     * 初始化修改用户密码页面
     */
    @PostMapping(value = "/initEditUserPwd")
    @ResponseBody
    public Map<String, Object> initEditUserPwd() {
        JSONObject jsonObject = userInfoService.initEditUserPwd();
        return jsonObject;
    }

    /**
     * 获取用户头像
     */
    @PostMapping(value = "/getUserAvatar")
    @ResponseBody
    public String getUserAvatar(HttpServletResponse response) throws IOException {
        String result = userInfoService.getUserAvatar(response);
        return result;
    }

    /**
     * 修改用户密码
     */
    @PostMapping(value = "/editUserPwd")
    @ResponseBody
    public JSONObject editUserPwd(@RequestBody LoginUser loginUser) throws UnsupportedEncodingException {
        JSONObject jsonObject = userInfoService.editUserPwd(loginUser);
        return jsonObject;
    }

    /**
     * 保存用户
     */
    @PostMapping(value = "/saveUser")
    @ResponseBody
    public JSONObject saveUser(@RequestBody User user) {
        JSONObject jsonObject = userInfoService.saveUser(user);
        return jsonObject;
    }
}
