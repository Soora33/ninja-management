package com.sora.ninja.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;

import com.sora.ninja.dao.UserNinjaDao;
import com.sora.domain.request.UserNinjaEntity;
import com.sora.ninja.service.UserNinjaService;


@Service("userNinjaService")
public class UserNinjaServiceImpl extends ServiceImpl<UserNinjaDao, UserNinjaEntity> implements UserNinjaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserNinjaEntity> page = this.page(
                new Query<UserNinjaEntity>().getPage(params),
                new QueryWrapper<UserNinjaEntity>()
        );

        return new PageUtils(page);
    }

}