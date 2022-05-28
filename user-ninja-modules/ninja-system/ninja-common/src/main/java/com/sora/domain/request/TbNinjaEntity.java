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
@TableName("tb_ninja")
public class TbNinjaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 忍者名
	 */
	private String name;
	/**
	 * 忍者等级外键
	 */
	private Integer levelId;
	/**
	 * 招募需求数量
	 */
	private Integer chip;
	/**
	 * 碎片价格
	 */
	private Integer price;

}
