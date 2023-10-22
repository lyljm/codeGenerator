package com.easyjava.output.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @description: 广告
 * @author: "eifying"
 * @date: 2023-10-22
 */
public class Advert implements Serializable {

	/**
	 * 广告id
	 */
	private Long id;

	/**
	 * 父级id，可能是post，也可能是评论，为0是广告
	 */
	private Long fatherid;

	/**
	 * 回复的人的id
	 */
	private Long userId;

	/**
	 * 被回复的的人的id
	 */
	private Long replayId;

	/**
	 * 跳转链接
	 */
	private String content;

	/**
	 * 图片地址
	 */
	private String photoUrl;

	/**
	 * 点赞数
	 */
	private Long like;

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