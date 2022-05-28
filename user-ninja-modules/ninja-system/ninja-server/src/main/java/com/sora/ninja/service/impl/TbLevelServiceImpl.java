package com.sora.ninja.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.domain.request.TbLevelEntity;
import com.sora.ninja.dao.TbLevelDao;
import com.sora.ninja.service.TbLevelService;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tbLevelService")
public class TbLevelServiceImpl extends ServiceImpl<TbLevelDao, TbLevelEntity> implements TbLevelService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        PageUtils pageUtils = null;
        // 尝试从redis中获取下拉框数据
        String levelList = (String)redisTemplate.opsForValue().get("levelList");
        if (levelList == null) {
            IPage<TbLevelEntity> page = this.page(
                    new Query<TbLevelEntity>().getPage(params),
                    new QueryWrapper<TbLevelEntity>()
            );
            pageUtils = new PageUtils(page);
            // 存入redis
            redisTemplate.opsForValue().set("levelList", JSONObject.toJSONString(pageUtils));
            return pageUtils;
        }
        // String -> PageUtils
        pageUtils = JSONObject.parseObject(levelList, PageUtils.class);
        return pageUtils;
    }

}