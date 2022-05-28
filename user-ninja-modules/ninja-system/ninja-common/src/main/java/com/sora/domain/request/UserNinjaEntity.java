package com.sora.domain.request;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:17:45
 */
@Data
@TableName("user_ninja")
public class UserNinjaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 忍者名
	 */
	private String ninjaName;
	/**
	 * 忍者等级
	 */
	private String level;

}
