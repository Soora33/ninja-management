package com.sora.ninja.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;

import com.sora.ninja.dao.TbNinjaDao;
import com.sora.domain.request.TbNinjaEntity;
import com.sora.ninja.service.TbNinjaService;


@Service("tbNinjaService")
public class TbNinjaServiceImpl extends ServiceImpl<TbNinjaDao, TbNinjaEntity> implements TbNinjaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbNinjaEntity> page = this.page(
                new Query<TbNinjaEntity>().getPage(params),
                new QueryWrapper<TbNinjaEntity>()
        );

        return new PageUtils(page);
    }

}