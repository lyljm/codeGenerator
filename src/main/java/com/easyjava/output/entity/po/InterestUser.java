package com.easyjava.output.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @description: 
 * @author: "eifying"
 * @date: 2023-10-22
 */
public class InterestUser implements Serializable {

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 关注的用户
	 */
	private Long interestUserId;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private LocalDateTime addTime;
}