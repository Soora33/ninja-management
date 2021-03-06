package com.sora.user.mapper;

import com.sora.user.entity.TbUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:19:45
 */
@Mapper
public interface TbUserDao extends BaseMapper<TbUserEntity> {

    /**
     * 根据累计充值金额获取用户当前等级
     * @param priceTotal
     * @return
     */
    String getUserLevelByTotal(@Param("priceTotal") Long priceTotal);
}
