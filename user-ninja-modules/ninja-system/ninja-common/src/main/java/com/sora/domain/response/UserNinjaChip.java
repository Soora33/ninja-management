package com.sora.domain.response;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @className: UserNinjaChip
 * @description: TODO
 * @date: 2022/05/29
 * @author: Sora33
 */
@Data
public class UserNinjaChip {
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
     * 招募需要
     */
    private Integer chip;
    /**
     * 用户持有碎片
     */
    private Integer userChip;
}
