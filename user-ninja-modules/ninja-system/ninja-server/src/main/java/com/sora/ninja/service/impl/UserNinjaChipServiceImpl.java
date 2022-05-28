package com.sora.ninja.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.domain.request.UserNinjaChipEntity;
import com.sora.ninja.dao.UserNinjaChipDao;
import com.sora.ninja.service.UserNinjaChipService;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userNinjaChipService")
public class UserNinjaChipServiceImpl extends ServiceImpl<UserNinjaChipDao, UserNinjaChipEntity> implements UserNinjaChipService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserNinjaChipEntity> page = this.page(
                new Query<UserNinjaChipEntity>().getPage(params),
                new QueryWrapper<UserNinjaChipEntity>()
        );

        return new PageUtils(page);
    }

}