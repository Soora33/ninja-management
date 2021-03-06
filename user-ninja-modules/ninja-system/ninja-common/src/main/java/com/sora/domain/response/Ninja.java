package com.sora.domain.response;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @className: Ninja
 * @description: 忍者列表
 * @date: 2022/05/28
 * @author: Sora33
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
     * 忍者等级
     */
    private String levelName;
    /**
     * 碎片价格
     */
    private Integer price;
}
