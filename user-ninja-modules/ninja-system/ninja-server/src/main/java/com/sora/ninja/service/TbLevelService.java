package com.sora.ninja.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sora.domain.request.TbLevelEntity;
import com.sora.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:17:45
 */
public interface TbLevelService extends IService<TbLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

