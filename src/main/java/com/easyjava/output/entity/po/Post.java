package com.easyjava.output.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @description: post表
 * @author: "eifying"
 * @date: 2023-10-22
 */
public class Post implements Serializable {

	/**
	 * postid
	 */
	private Long id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 主题id
	 */
	private Long subjectId;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 图片地址
	 */
	private String photoUrl;

	/**
	 * post状态，0草稿，1未审核，2已发布
	 */
	private Integer status;

	/**
	 * 点赞数
	 */
	private Long liked;

	/**
	 * 评论数
	 */
	@JsonIgnore
	private Long comment;

	/**
	 * 收藏数
	 */
	private Long collect;

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
	 * 更新版本号
	 */
	private Integer version;
}