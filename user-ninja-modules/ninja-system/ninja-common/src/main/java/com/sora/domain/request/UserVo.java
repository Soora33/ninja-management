package com.sora.domain.request;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @className: UserVo
 * @description: TODO
 * @date: 2022/05/29
 * @author: Sora33
 */
@Data
public class UserVo {

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
