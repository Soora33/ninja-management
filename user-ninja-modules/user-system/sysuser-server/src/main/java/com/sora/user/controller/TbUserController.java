package com.sora.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sora.user.entity.TbUserEntity;
import com.sora.user.service.TbUserService;
import com.sora.utils.PageUtils;
import com.sora.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author Sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:19:45
 */
@RestController
@RequestMapping("user/tbuser")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * <h1>购买碎片</h1>
     * <p>
     *     远程调用获取忍者和用户信息,随后判断用户是否有购买次数,金币是否足够
     *     购买完成之后重新赋值用户等级
     * </p>
     */
    @RequestMapping("/buyChip/{ninjaId}/{userId}")
    public R buyChip(@PathVariable("ninjaId") Integer ninjaId,@PathVariable("userId") Integer userId){
        return tbUserService.buyChip(ninjaId, userId);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tbUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		TbUserEntity tbUser = tbUserService.getById(id);

        return R.ok().put("tbUser", JSONObject.toJSONString(tbUser));
    }


    /**
     * 根据用户手机号获取用户
     * @Params tel
     */
    @RequestMapping("/infoByTel/{tel}")
    public R getUserByTel(@PathVariable("tel") String tel){
		TbUserEntity tbUser = tbUserService.getUserByTel(tel);
        if (tbUser == null) {
            return R.error(500,"用户名或密码错误!");
        }
        return R.ok().put("tbUser", JSONObject.toJSONString(tbUser));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TbUserEntity tbUser){
		tbUserService.save(tbUser);

        return R.ok();
    }

    /**
     * <h1 style=color:red>用户充值余额</h1>
     * 1 修改用户累计充值金额
     * 2 加上对应充值后的金额
     * 3 修改用户vip等级
     */
    @RequestMapping("/update/{userId}/{gold}")
    public R update(@PathVariable("userId") Integer userId, @PathVariable("gold") Integer price) {
        return tbUserService.updateGlodAndPriceByUserId(userId, price);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		tbUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
