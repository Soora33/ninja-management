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
 * @author sora33
 * @email 2097665736inori@gmail.com
 * @date 2022-05-28 17:19:45
 */
@RestController
@RequestMapping("user/tbuser")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 购买碎片
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

        return R.ok().put("tbUser", tbUser);
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
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TbUserEntity tbUser){
		tbUserService.updateById(tbUser);

        return R.ok();
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
