package com.sora.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sora.utils.PageUtils;
import com.sora.user.entity.LevelConsultEntity;

import java.util.Map;

/**
 * 
 *
 * @author sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:19:45
 */
public interface LevelConsultService extends IService<LevelConsultEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

