package com.sora.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;

import com.sora.user.mapper.TbUserDao;
import com.sora.user.entity.TbUserEntity;
import com.sora.user.service.TbUserService;


@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUserEntity> implements TbUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbUserEntity> page = this.page(
                new Query<TbUserEntity>().getPage(params),
                new QueryWrapper<TbUserEntity>()
        );

        return new PageUtils(page);
    }

}