package com.sora.domain.response;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @className: Ninja
 * @description: 忍者列表
 * @date: 2022/05/28
 * @author: 王致翔
 */
@Data
public class Ninja {
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
    private String levelName;
    /**
     * 碎片价格
     */
    private Integer price;
}
