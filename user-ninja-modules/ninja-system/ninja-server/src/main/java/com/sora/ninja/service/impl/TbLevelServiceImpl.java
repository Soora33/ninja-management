package com.sora.ninja.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.domain.request.TbLevelEntity;
import com.sora.ninja.dao.TbLevelDao;
import com.sora.ninja.service.TbLevelService;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tbLevelService")
public class TbLevelServiceImpl extends ServiceImpl<TbLevelDao, TbLevelEntity> implements TbLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbLevelEntity> page = this.page(
                new Query<TbLevelEntity>().getPage(params),
                new QueryWrapper<TbLevelEntity>()
        );

        return new PageUtils(page);
    }

}