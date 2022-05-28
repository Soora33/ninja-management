package com.sora.ninja.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.domain.request.TbNinjaEntity;
import com.sora.domain.response.Ninja;
import com.sora.ninja.dao.TbNinjaDao;
import com.sora.ninja.service.TbNinjaService;
import com.sora.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("tbNinjaService")
public class TbNinjaServiceImpl extends ServiceImpl<TbNinjaDao, TbNinjaEntity> implements TbNinjaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // 获取分页信息
        String page = params.get("page").toString();
        String pageSize = params.get("pageSize").toString();
        // 分页
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        // 获取忍者集合
        List<Ninja> ninjaList = baseMapper.getNinjaList(params);
        PageInfo<Ninja> ninjaPageInfo = new PageInfo<>(ninjaList);
        return new PageUtils(ninjaList, (int)ninjaPageInfo.getTotal(), Integer.parseInt(pageSize), Integer.parseInt(page));
    }

}