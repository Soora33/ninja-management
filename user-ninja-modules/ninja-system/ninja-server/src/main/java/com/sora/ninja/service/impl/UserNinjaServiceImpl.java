package com.sora.ninja.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.domain.request.TbLevelEntity;
import com.sora.domain.request.TbNinjaEntity;
import com.sora.domain.request.UserNinjaChipEntity;
import com.sora.domain.request.UserNinjaEntity;
import com.sora.ninja.dao.UserNinjaDao;
import com.sora.ninja.service.UserNinjaService;
import com.sora.remote.NinjaLevelRemoteServer;
import com.sora.remote.NinjaRemoteServer;
import com.sora.remote.NinjaUserChipRemoteServer;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;
import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userNinjaService")
@Log4j2
public class UserNinjaServiceImpl extends ServiceImpl<UserNinjaDao, UserNinjaEntity> implements UserNinjaService {

    @Autowired
    private NinjaUserChipRemoteServer ninjaUserChipRemoteServer;


    @Autowired
    private NinjaRemoteServer ninjaRemoteServer;

    @Autowired
    private NinjaLevelRemoteServer ninjaLevelRemoteServer;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserNinjaEntity> page = this.page(
                new Query<UserNinjaEntity>().getPage(params),
                new QueryWrapper<UserNinjaEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * <h2>增加用户的忍者</h2>
     * <p>
     *     判断用户碎片是否足够,记得减去忍者碎片,重新赋值用户碎片数量
     * </p>
     */
    @Override
    public R saveNinja(Integer userId, Integer ninjaId) {
        long startTime = System.currentTimeMillis();
        // 根据用户id和忍者id获取用户忍者碎片个数
        R userNinjaChip = ninjaUserChipRemoteServer.getUserNinjaChip(ninjaId, userId);
        Object userNinja = userNinjaChip.get("userNinjaChip");
        UserNinjaChipEntity ninjaChipVo = JSONObject.parseObject(userNinja.toString(), UserNinjaChipEntity.class);
        // 根据忍者id获取忍者
        R ninjaInfo = ninjaRemoteServer.info(ninjaId);
        Object tbNinja = ninjaInfo.get("tbNinja");
        TbNinjaEntity ninjaVo = JSONObject.parseObject(tbNinja.toString(), TbNinjaEntity.class);

        // 判断碎片数量是否足够
        if (ninjaChipVo.getChip() == 0 || ninjaChipVo.getChip() == null) {
            log.info("用户[{}]招募忍者[{}]失败,碎片不足,用户持有碎片个数[{}],耗时[{}]MS", userId, ninjaId,
                    ninjaChipVo.getChip(), System.currentTimeMillis() - startTime);
            return R.error(500, "招募失败,碎片不足");
        }
        if (ninjaChipVo.getChip().intValue() >= (ninjaVo.getChip()).intValue()) {
            log.info("用户[{}]招募忍者[{}]失败,碎片不足,用户持有碎片个数[{}],耗时[{}]MS", userId, ninjaId,
                    ninjaChipVo.getChip(), System.currentTimeMillis() - startTime);
            // 修改用户拥有的忍者碎片数量 用户拥有的碎片 - 忍者所需碎片
            ninjaChipVo.setChip(ninjaChipVo.getChip() - ninjaVo.getChip());
            // 创建用户忍者对象
            UserNinjaEntity ninjaEntity = new UserNinjaEntity();
            ninjaEntity.setUserId(userId);
            ninjaEntity.setNinjaName(ninjaVo.getName());
            /**
             * 根据忍者等级id获取忍者等级对象
             */
            R info = ninjaLevelRemoteServer.info(ninjaVo.getLevelId());
            Object tbLevel = info.get("tbLevel");
            TbLevelEntity levelVp = JSONObject.parseObject(tbLevel.toString(), TbLevelEntity.class);

            // 赋值忍者的等级
            ninjaEntity.setLevel(levelVp.getLevel());

            // 添加用户持有忍者
            baseMapper.insert(ninjaEntity);
            // 修改用户碎片数量
            ninjaUserChipRemoteServer.update(ninjaChipVo);

            log.info("用户[{}]成功招募忍者[{}],剩余碎片[{}],耗时[{}]MS",userId,ninjaId,ninjaChipVo.getChip(),System.currentTimeMillis() - startTime);
            return R.ok("招募成功");
        } else {
            log.info("用户[{}]招募失败,忍者[{}],剩余碎片[{}],耗时[{}]MS",userId,ninjaId,ninjaChipVo.getChip(),System.currentTimeMillis() - startTime);
            return R.error(500, "招募失败,碎片不足");
        }
    }

}