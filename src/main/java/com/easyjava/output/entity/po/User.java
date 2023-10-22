package com.easyjava.output.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @description: 用户表
 * @author: "eifying"
 * @date: 2023-10-22
 */
public class User implements Serializable {

	/**
	 * 用户表ID
	 */
	private String id;

	/**
	 * 邀请者
	 */
	private String inviter;

	/**
	 * 用户名称
	 */
	private String username;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 真实姓名
	 */
	private String trueName;

	/**
	 * 性别：0 未知， 1男， 2女
	 */
	private Integer gender;

	/**
	 * 个性标签
	 */
	private String label;

	/**
	 * 背景图片
	 */
	private String backUrl;

	/**
	 * 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private LocalDateTime birthday;

	/**
	 * 个人分享图片
	 */
	private String shareUrl;

	/**
	 * 最近一次登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLoginTime;

	/**
	 * 最近一次登录IP地址
	 */
	private String lastLoginIp;

	/**
	 * 0 普通用户，1 VIP用户，2 高级VIP用户
	 */
	private Integer userLevel;

	/**
	 * 用户等级
	 */
	private BigDecimal integral;

	/**
	 * 用户昵称或网络名称
	 */
	private String nickName;

	/**
	 * 用户手机号码
	 */
	private String mobile;

	/**
	 * 用户头像图片
	 */
	private String avatarUrl;

	/**
	 * 微信登录openid
	 */
	private String openid;

	/**
	 * 微信登录会话KEY
	 */
	private String sessionKey;

	/**
	 * 0 可用, 1 禁用, 2 注销
	 */
	private Integer status;

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
	 * 租户ID，用于分割多个租户
	 */
	private String tenantId;

	/**
	 * 更新版本号
	 */
	private Integer version;
}