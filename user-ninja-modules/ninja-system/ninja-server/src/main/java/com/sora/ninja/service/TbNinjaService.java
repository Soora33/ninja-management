package com.sora.ninja.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sora.domain.request.TbNinjaEntity;
import com.sora.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:17:45
 */
public interface TbNinjaService extends IService<TbNinjaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

