package com.easyjava.output.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @description: 评论表
 * @author: "eifying"
 * @date: 2023-10-22
 */
public class Comment implements Serializable {

	/**
	 * 评论id
	 */
	private Long id;

	/**
	 * 父级id，可能是post，也可能是评论
	 */
	private Long fatherid;

	/**
	 * 回复的人的id
	 */
	private String userId;

	/**
	 * 被回复的人的id
	 */
	private String replayId;

	/**
	 * 图文的id
	 */
	private Long postId;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 图片地址
	 */
	private String photoUrl;

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