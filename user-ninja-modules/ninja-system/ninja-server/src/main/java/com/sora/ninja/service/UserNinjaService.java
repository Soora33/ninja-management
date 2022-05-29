package com.sora.ninja.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sora.utils.PageUtils;
import com.sora.domain.request.UserNinjaEntity;
import com.sora.utils.R;

import java.util.Map;

/**
 * 
 *
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:17:45
 */
public interface UserNinjaService extends IService<UserNinjaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * <h2>增加用户的忍者</h2>
     * <p>
     *     判断用户碎片是否足够,记得减去忍者碎片,重新赋值用户碎片数量
     * </p>
     */
    R saveNinja(Integer userId, Integer ninjaId);
}

