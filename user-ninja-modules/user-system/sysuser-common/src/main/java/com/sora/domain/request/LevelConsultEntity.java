package com.sora.user.entity;

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
 * @date 2022-05-28 17:19:45
 */
@Data
@TableName("level_consult")
public class LevelConsultEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 等级名
	 */
	private String name;
	/**
	 * 金额需求
	 */
	private Integer goldCount;

}
