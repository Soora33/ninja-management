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
@TableName("user_ninja_chip")
public class UserNinjaChipEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 忍者id
	 */
	@TableId
	private Integer ninjaId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 碎片数量
	 */
	private Integer chip;

}
