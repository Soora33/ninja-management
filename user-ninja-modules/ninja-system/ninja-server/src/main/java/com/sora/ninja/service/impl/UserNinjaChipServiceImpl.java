package com.sora.ninja.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sora.domain.request.UserNinjaChipEntity;
import com.sora.domain.response.UserNinjaChip;
import com.sora.ninja.dao.UserNinjaChipDao;
import com.sora.ninja.service.UserNinjaChipService;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;
import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userNinjaChipService")
@Log4j2
public class UserNinjaChipServiceImpl extends ServiceImpl<UserNinjaChipDao, UserNinjaChipEntity> implements UserNinjaChipService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserNinjaChipEntity> page = this.page(
                new Query<UserNinjaChipEntity>().getPage(params),
                new QueryWrapper<UserNinjaChipEntity>()
        );
        return new PageUtils(page);
    }


    /**
     * <h1>修改用户持有的忍者碎片个数</h1>
     * 根据用户id和忍者id修改 如果存在数量+1 不在插入数据
     */
    @Override
    public R updateNinjaChip(Integer userId, Integer ninjaId) {
        long startTime = System.currentTimeMillis();
        // 根据用户id和忍者id获取该信息
        UserNinjaChipEntity userNinjaChip = baseMapper.selectOne(new QueryWrapper<UserNinjaChipEntity>() {{
            eq("user_id", userId);
            eq("ninja_id", ninjaId);
        }});
        if (userNinjaChip == null) {
            UserNinjaChipEntity userNinjaChips = new UserNinjaChipEntity();
            userNinjaChips.setUserId(userId);
            userNinjaChips.setNinjaId(ninjaId);
            userNinjaChips.setChip(1);
            // 插入新数据
            baseMapper.insert(userNinjaChips);
            log.info("碎片数量同步完成,用户当前拥有忍者id[{}]碎片个数为[{}],耗时[{}]MS",
                    ninjaId, userNinjaChips.getChip(), System.currentTimeMillis() - startTime);
            return R.ok("碎片同步完成");
        }
        // 修改碎片数量+1
        userNinjaChip.setChip(userNinjaChip.getChip() + 1);
        baseMapper.update(userNinjaChip,new QueryWrapper<UserNinjaChipEntity>(){{
            eq("user_id", userId);
            eq("ninja_id", ninjaId);
        }});
        log.info("碎片数量同步完成,用户当前拥有忍者id[{}]碎片个数为[{}],耗时[{}]MS",
                ninjaId, userNinjaChip.getChip(),System.currentTimeMillis() - startTime);
        return R.ok("碎片同步完成");
    }

    /**
     * 获取用户忍者碎片数量的忍者列表
     */
    @Override
    public PageUtils getUserNinjaChipList(Map<String, Object> params) {
        // 获取分页信息
        String page = params.get("page").toString();
        String pageSize = params.get("pageSize").toString();
        // 分页
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        // 获得集合
        List<UserNinjaChip> userNinjaChipList = baseMapper.getUserNinjaChipList();
        PageInfo<UserNinjaChip> userNinjaChipPageInfo = new PageInfo<>(userNinjaChipList);
        return new PageUtils(userNinjaChipList, (int)userNinjaChipPageInfo.getTotal(), Integer.parseInt(page), Integer.parseInt(pageSize));
    }

    /**
     * 根据用户id和忍者id获取对象
     */
    @Override
    public UserNinjaChipEntity getUserNinjaChip(Integer ninjaId, Integer userId) {
        return baseMapper.selectOne(new QueryWrapper<UserNinjaChipEntity>(){{
            eq("user_id", userId);
            eq("ninja_id", ninjaId);
        }});
    }

}