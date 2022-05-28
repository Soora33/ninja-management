package com.sora.domain.response;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @className: NinjaVo
 * @description: TODO
 * @date: 2022/05/28
 * @author: 王致翔
 */
@Data
public class NinjaVo {

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
