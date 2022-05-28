package com.sora.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;

import com.sora.user.mapper.LevelConsultDao;
import com.sora.user.entity.LevelConsultEntity;
import com.sora.user.service.LevelConsultService;


@Service("levelConsultService")
public class LevelConsultServiceImpl extends ServiceImpl<LevelConsultDao, LevelConsultEntity> implements LevelConsultService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LevelConsultEntity> page = this.page(
                new Query<LevelConsultEntity>().getPage(params),
                new QueryWrapper<LevelConsultEntity>()
        );

        return new PageUtils(page);
    }

}