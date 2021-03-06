package com.sora.ninja.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sora.domain.request.UserNinjaChipEntity;
import com.sora.ninja.service.UserNinjaChipService;
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
 * @date 2022-05-28 17:17:45
 */
@RestController
@RequestMapping("ninja/userninjachip")
public class UserNinjaChipController {
    @Autowired
    private UserNinjaChipService userNinjaChipService;

    /**
     * 获取用户忍者碎片数量的忍者列表
     */
    @RequestMapping("/list")
    public R getUserNinjaChipList(@RequestParam Map<String, Object> params){
        PageUtils page = userNinjaChipService.getUserNinjaChipList(params);
        return R.ok().put("page", page);
    }


    /**
     * 根据用户id和忍者id获取对象
     */
    @RequestMapping("/info/{ninjaId}/{userId}")
    public R getUserNinjaChip(@PathVariable("ninjaId") Integer ninjaId,@PathVariable("userId") Integer userId){
        UserNinjaChipEntity userNinjaChip = userNinjaChipService.getUserNinjaChip(ninjaId, userId);

        return R.ok().put("userNinjaChip", JSONObject.toJSONString(userNinjaChip));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ninjaId}")
    public R info(@PathVariable("ninjaId") Integer ninjaId){
		UserNinjaChipEntity userNinjaChip = userNinjaChipService.getById(ninjaId);

        return R.ok().put("userNinjaChip", userNinjaChip);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserNinjaChipEntity userNinjaChip){
		userNinjaChipService.save(userNinjaChip);

        return R.ok();
    }

    /**
     * <h1>修改用户持有的忍者碎片个数</h1>
     * 根据用户id和忍者id修改 如果存在数量+1 不在插入数据
     */
    @RequestMapping("/update/{userId}/{ninjaId}")
    public R updateNinjaChip(@PathVariable("userId") Integer userId, @PathVariable("ninjaId") Integer ninjaId){
		return userNinjaChipService.updateNinjaChip(userId,ninjaId);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserNinjaChipEntity userNinjaChip){
		userNinjaChipService.update(userNinjaChip,new QueryWrapper<UserNinjaChipEntity>(){{
            eq("user_id", userNinjaChip.getUserId());
            eq("ninja_id", userNinjaChip.getNinjaId());
        }});

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ninjaIds){
		userNinjaChipService.removeByIds(Arrays.asList(ninjaIds));

        return R.ok();
    }

}
