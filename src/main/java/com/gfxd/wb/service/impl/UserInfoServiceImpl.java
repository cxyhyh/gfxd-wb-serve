package com.gfxd.wb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.constant.CommonApi;
import com.gfxd.wb.entity.model.LoginUser;
import com.gfxd.wb.entity.model.User;
import com.gfxd.wb.service.UserInfoService;
import com.gfxd.wb.utils.HttpUtils;
import com.gfxd.wb.utils.SHAUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: hyh
 * Date: 2022/8/12 13:49
 * Describe:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /**服务器ip*/
    @Value("${http.hostname}")
    private String hostname;
    /**token*/
    @Value("${http.token}")
    private String token;
    @Resource
    HttpUtils httpUtils;

    public String userId;

    public String currentUser;


    @Override
    public Map<String,Object> getCurrentUser() {
        Map<String,Object> map = new HashMap<>();
        map.put("currentUserId",userId);
        map.put("currentUser",currentUser);
        return map;
    }

    @Override
    public JSONObject login(LoginUser loginUser, HttpSession session) throws UnsupportedEncodingException {
        String loginName = loginUser.getLoginName();
        String password = loginUser.getPassword();
        String decodePwd = new String(Base64.getDecoder().decode(password), "UTF-8");
        String sha512 = SHAUtils.SHA512Encrypt(decodePwd);
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("loginName", loginName);
        map.add("password", sha512);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.LOGIN_URL,map);
        String currentUserId = result.getJSONObject("data").getString("loginUserId");
        String currentUserType = result.getJSONObject("data").getString("userType");
        session.setAttribute("currentUserId", currentUserId);
        session.setAttribute("currentUser", loginUser.getLoginName());
        session.setAttribute("currentUserType", currentUserType);
        userId =  session.getAttribute("currentUserId").toString();
        currentUser = session.getAttribute("currentUser").toString();
        return result;
    }

    @Override
    public JSONObject initListUserPage() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.INIT_USER_URL,map);
        return result;
    }

    @Override
    public JSONObject initEditUser() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("id", userId);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.INIT_EDIT_USER_URL,map);
        return result;
    }

    @Override
    public JSONObject initEditUserPwd() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("userId", userId);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.INIT_USER_PWD_URL,map);
        return result;
    }

    @Override
    public String getUserAvatar(HttpServletResponse response) throws IOException {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("userId", userId);
        String result = httpUtils.postForAvatar(hostname+CommonApi.USER_AVATAR_URL,map,response);
        return result;
    }

    @Override
    public JSONObject editUserPwd(LoginUser loginUser) throws UnsupportedEncodingException {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        loginUser.setCurrentUser(userId);
        String userId = loginUser.getUserId();
        String currentUser = loginUser.getCurrentUser();
        String newPassword = loginUser.getNewPassword();
        String oldPassword = loginUser.getOldPassword();
        String decodePwd = new String(Base64.getDecoder().decode(newPassword), "UTF-8");
        map.add("currentUser", currentUser);
        map.add("userId", userId);
        map.add("newPassword", decodePwd);
        map.add("oldPassword", oldPassword);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.EDIT_USER_PWD_URL,map);
        return result;
    }

    @Override
    public JSONObject saveUser(User user) {
        user.setToken(token);
        JSONObject result = httpUtils.postForLoginUser(hostname+CommonApi.SAVE_USER_URL,user);
        return result;
    }
}
