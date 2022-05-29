package com.sora.ninja.dao;

import com.sora.domain.request.TbNinjaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sora.domain.response.Ninja;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:17:45
 */
@Mapper
public interface TbNinjaDao extends BaseMapper<TbNinjaEntity> {

    /**
     * 获取忍者列表
     * @param params
     * @return
     */
    List<Ninja> getNinjaList(Map<String, Object> params);

}
