package com.sora.ninja.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sora.utils.PageUtils;
import com.sora.domain.request.UserNinjaChipEntity;
import com.sora.utils.R;

import java.util.Map;

/**
 * 
 *
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:17:45
 */
public interface UserNinjaChipService extends IService<UserNinjaChipEntity> {

    PageUtils queryPage(Map<String, Object> params);


    /**
     * <h1>修改用户持有的忍者碎片个数</h1>
     * 根据用户id和忍者id修改 如果存在数量+1 不在插入数据
     */
    R updateNinjaChip(Integer userId, Integer ninjaId);

    /**
     * 获取用户忍者碎片数量的忍者列表
     */
    PageUtils getUserNinjaChipList(Map<String, Object> params);

    /**
     * 根据用户id和忍者id获取对象
     */
    UserNinjaChipEntity getUserNinjaChip(Integer ninjaId, Integer userId);
}

