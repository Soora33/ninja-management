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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
     * <h1>购买碎片</h1>
     * <p>
     *     远程调用获取忍者和用户信息,随后判断用户是否有购买次数,金币是否足够
     *     购买完成之后重新赋值用户等级
     * </p>
     */
    @Override
    public R buyChip(Integer ninjaId, Integer userId) {
        long startTime = System.currentTimeMillis();
        log.info("开始远程调用获取忍者信息,获取忍者id为[{}]的信息", ninjaId);
        // 获取忍者信息
        R ninjaR = null;
        ninjaR = ninjaRemoteServer.info(ninjaId);
//        try {
//            ninjaR = ninjaRemoteServer.info(ninjaId);
//        } catch (Exception e) {
//            log.info("远程调用获取id为[{}]的忍者信息失败,错误信息:[{}],耗时[{}]MS", ninjaId, JSONObject.toJSONString(ninjaR), System.currentTimeMillis() - startTime);
//        }
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
        /**
         * 判断用户vip等级 如果V8以上七折
         */
        int losePrice = 0;
        if (Integer.parseInt(user.getVipLevel().substring(1)) > 8) {
            losePrice = (int)(ninjaVo.getPrice() * 0.7);
        }
        user.setBuyCount(user.getBuyCount() - 1);
        // 赋值用户剩余金额
        user.setGold(user.getGold() - losePrice);
        baseMapper.updateById(user);
        log.info("用户id[{}]购买忍者[{}]成功,用户当前余额[{}],耗时[{}]MS", user.getId(), ninjaVo.getName(), user.getGold(), System.currentTimeMillis() - startTime);
        return R.ok("购买碎片成功");
    }

    /**
     * <h1 style=color:red>用户充值余额</h1>
     * 1 修改用户累计充值金额
     * 2 加上对应充值后的金额
     * 3 修改用户vip等级
     */
    @Override
    public R updateGlodAndPriceByUserId(Integer userId, Integer price) {
        long startTime = System.currentTimeMillis();
        // 根据用户id获取用户
        TbUserEntity user = baseMapper.selectById(userId);
        /**
         * 	金币充值充值100元以上奖励10%的额外金币，即充值100元，到账1100金币（5分）
         */
        if (price > 1000) {
            // 增加的钱
            int addPrice = (int) (price * 0.1);
            price += addPrice;
        }
        // 转换为金币
        Integer gold = price * 10;
        // 修改充值总金额
        user.setPriceTotal(user.getPriceTotal() + price);
        // 修改金币
        user.setGold(user.getGold() + gold);
        // 获取用户当前vip等级
        String vipLevel = baseMapper.getUserLevelByTotal(user.getPriceTotal());
        user.setVipLevel(vipLevel);
        // 修改用户
        int update = baseMapper.updateById(user);
        if (update > 0) {
            log.info("用户id[{}]充值成功,充值金额[{}],耗时[{}]MS", user.getId(), price, System.currentTimeMillis() - startTime);
            return R.ok("充值成功");
        }
        log.info("用户id[{}]充值失败,充值金额[{}],耗时[{}]MS", user.getId(), price, System.currentTimeMillis() - startTime);
        return R.error(500, "充值失败");
    }


    /**
     * 定时任务 每天晚上24点刷新每日购买次数
     */
    @Scheduled(cron = "59 59 23 * * *")
    public void updateBuyCount() {
        // 获取所有用户
        List<TbUserEntity> userEntities = baseMapper.selectList(null);
        for (TbUserEntity userEntity : userEntities) {
            // 获得用户VIP等级
            Integer buyCount = Integer.parseInt(userEntity.getVipLevel().substring(1));
            userEntity.setBuyCount(buyCount);
            baseMapper.updateById(userEntity);
            log.info(new Date().toLocaleString() + "每次购买次数刷新成功");
        }
    }
}