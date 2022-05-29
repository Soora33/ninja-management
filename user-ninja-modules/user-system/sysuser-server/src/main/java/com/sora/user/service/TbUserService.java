package com.sora.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sora.utils.PageUtils;
import com.sora.user.entity.TbUserEntity;
import com.sora.utils.R;

import java.util.Map;

/**
 * 
 *
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:19:45
 */
public interface TbUserService extends IService<TbUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户手机号获取用户
     * @Params tel
     */
    TbUserEntity getUserByTel(String tel);

    /**
     * <h1>购买碎片</h1>
     * <p>
     *     远程调用获取忍者和用户信息,随后判断用户是否有购买次数,金币是否足够
     *     购买完成之后重新赋值用户等级
     * </p>
     */
    R buyChip(Integer ninjaId, Integer userId);

    /**
     * <h1 style=color:red>用户充值余额</h1>
     * 1 修改用户累计充值金额
     * 2 加上对应充值后的金额
     * 3 修改用户vip等级
     */
    R updateGlodAndPriceByUserId(Integer userId, Integer price);
}

