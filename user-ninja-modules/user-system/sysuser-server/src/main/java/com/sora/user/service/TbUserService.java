package com.sora.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sora.utils.PageUtils;
import com.sora.user.entity.TbUserEntity;
import com.sora.utils.R;

import java.util.Map;

/**
 * 
 *
 * @author sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:19:45
 */
public interface TbUserService extends IService<TbUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户手机号获取用户
     * @Params tel
     */
    TbUserEntity getUserByTel(String tel);

    /**
     * 购买碎片
     */
    R buyChip(Integer ninjaId, Integer userId);
}

