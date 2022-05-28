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
@TableName("tb_user")
public class TbUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * vip等级
	 */
	private String vipLevel;
	/**
	 * 金币数量
	 */
	private Long gold;
	/**
	 * 今日可购买次数
	 */
	private Integer buyCount;
	/**
	 * 手机号
	 */
	private String tel;
	/**
	 * 累计充值余额
	 */
	private Long priceTotal;

}
