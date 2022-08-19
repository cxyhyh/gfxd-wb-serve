package com.gfxd.wb.service;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.entity.model.LoginUser;
import com.gfxd.wb.entity.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Author: hyh
 * Date: 2022/8/12 13:59
 * Describe:
 */
public interface UserInfoService {

    Map<String,Object> getCurrentUser();

    JSONObject login(LoginUser loginUser, HttpSession session) throws UnsupportedEncodingException;

    JSONObject initListUserPage();

    JSONObject initEditUser();

    JSONObject initEditUserPwd();

    String getUserAvatar(HttpServletResponse response) throws IOException;

    JSONObject editUserPwd(LoginUser loginUser) throws UnsupportedEncodingException;

    JSONObject saveUser(User user);
}
