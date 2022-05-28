package com.sora.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sora.domain.response.NinjaVo;
import com.sora.remote.NinjaRemoteServer;
import com.sora.remote.NinjaUserChipRemoteServer;
import com.sora.user.entity.TbUserEntity;
import com.sora.user.mapper.TbUserDao;
import com.sora.user.service.TbUserService;
import com.sora.utils.PageUtils;
import com.sora.utils.Query;
import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tbUserService")
@Log4j2
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUserEntity> implements TbUserService {

    @Autowired
    private NinjaRemoteServer ninjaRemoteServer;

    @Autowired
    private NinjaUserChipRemoteServer ninjaUserChipRemoteServer;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbUserEntity> page = this.page(
                new Query<TbUserEntity>().getPage(params),
                new QueryWrapper<TbUserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据用户手机号获取用户
     * @Params tel
     */
    @Override
    public TbUserEntity getUserByTel(String tel) {
        QueryWrapper<TbUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("tel", tel);

        return baseMapper.selectOne(wrapper);
    }

    /**
     * 购买碎片
     */
    @Override
    public R buyChip(Integer ninjaId, Integer userId) {
        long startTime = System.currentTimeMillis();
        log.info("开始远程调用获取忍者信息,获取忍者id为[{}]的信息", ninjaId);
        // 获取忍者信息
        R ninjaR = null;
        try {
            ninjaR = ninjaRemoteServer.info(ninjaId);
        } catch (Exception e) {
            log.info("远程调用获取id为[{}]的忍者信息失败,错误信息:[{}],耗时[{}]MS", ninjaId, JSONObject.toJSONString(ninjaR), System.currentTimeMillis() - startTime);
        }
        Object tbNinja = ninjaR.get("tbNinja").toString();
        if (tbNinja == null) {
            log.info("远程调用获取id为[{}]的忍者信息失败,忍者为空,错误信息:[{}],耗时[{}]MS", ninjaId, JSONObject.toJSONString(ninjaR), System.currentTimeMillis() - startTime);
        }
        // 转为忍者VO
        NinjaVo ninjaVo = JSONObject.parseObject(tbNinja.toString(), NinjaVo.class);
        // 获取用户
        TbUserEntity user = baseMapper.selectById(userId);
        // 判断是否有足够的次数
        if (user.getBuyCount() <= 0) {
            log.info("用户id[{}]的今日购买次数已用尽,购买次数为[{}]",user.getId(), user.getBuyCount());
            return R.error(500, "您今日购买次数已用尽");
        }
        // 判断用户是否有足够的金额
        if (ninjaVo.getPrice() > user.getGold()) {
            log.info("用户id[{}]的金币不足,余额为[{}]",user.getId(), user.getGold());
            return R.error(500, "用户金币不足");
        }
        /**
         * 修改用户金币 -> 本方法修改
         * 修改用户拥有的该忍者碎片个数 -> 采用feign调用修改
         * 修改用户的购买次数 -> 本方法修改
         */
        // 使用远程调用修改用户碎片状况
        ninjaUserChipRemoteServer.updateNinjaChip(userId, ninjaId);
        // 修改用户状态
        user.setBuyCount(user.getBuyCount() - 1);
        user.setGold(user.getGold() - ninjaVo.getPrice());
        baseMapper.updateById(user);
        log.info("用户id[{}]购买忍者[{}]成功,用户当前余额[{}],耗时[{}]MS", user.getId(), ninjaVo.getName(), user.getGold(), System.currentTimeMillis() - startTime);
        return R.ok("购买碎片成功");
    }
}