/*
 * All rights Reserved, Designed By DataDriver
 * Copyright:    DataDriver.Inc
 * Company:      Zhuo Wo Infomation Technology (ShangHai) CO.LTD
 */
package com.gfxd.wb.entity.model;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: 用户类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态
	 */

	private String status;

	/**
	 * 状态名称
	 */
	private String statusName;

	/**
	 * 用户类型
	 */
	private String userType;

	/**
	 * 工号
	 */
	private String workNumber;

    /**
     * 头像
     */
	private String avatar;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 移动电话
     */
	private String mobile;
    /**
     * 性别
     */
	private String sex;
    /**
     * 性别名称
     */
	private String sexName;
    /**
     * 分页保留选中状态 唯一列（默认主键）
     */
	private String fieldId;
	/***
	 * 所属企业，只有在为财务用户时才有用
	 */
	private String entId;

	private String deptId;

	/**
	 * 部门名称
	 */
	private String deptName;

	private String mailSortNumber;

	private String token;


}
