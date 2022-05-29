package com.sora.ninja.dao;

import com.sora.domain.request.UserNinjaChipEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sora.domain.response.UserNinjaChip;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:17:45
 */
@Mapper
public interface UserNinjaChipDao extends BaseMapper<UserNinjaChipEntity> {

    List<UserNinjaChip> getUserNinjaChipList();
}
