/**
 * Author: hyh
 * Date: 2022/8/4 10:48
 * Describe:
 */

package com.gfxd.wb.entity.model;

import lombok.Data;

/**
 * Author: hyh
 * Date: 2022/8/4 10:48
 * Describe:
 */
@Data
public class LoginUser {

    private String loginName;

    private String password;

    private String userId;

    private String oldPassword;

    private String newPassword;

    private String token;

    private String currentUser;



}
