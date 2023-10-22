package com.easyjava.output.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @description: 管理员表
 * @author: "eifying"
 * @date: 2023-10-22
 */
public class Admin implements Serializable {

	/**
	 * 管理员表ID
	 */
	private String id;

	/**
	 * 管理员名称
	 */
	private String username;

	/**
	 * 管理员密码
	 */
	private String password;

	/**
	 * 最近一次登录IP地址
	 */
	private String lastLoginIp;

	/**
	 * 最近一次登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLoginTime;

	/**
	 * 微信openid
	 */
	private String openid;

	/**
	 * 用户手机号码
	 */
	private String mobile;

	/**
	 * 管理员邮箱
	 */
	private String mail;

	/**
	 * 性别：0 未知， 1男， 2 女
	 */
	private Integer gender;

	/**
	 * 头像图片
	 */
	private String avatar;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private LocalDateTime addTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/**
	 * 逻辑删除
	 */
	private Integer deleted;

	/**
	 * 角色列表
	 */
	private String roleIds;

	/**
	 * 租户ID，用于分割多个租户
	 */
	private String tenantId;

	/**
	 * 更新版本号
	 */
	private Integer version;
}