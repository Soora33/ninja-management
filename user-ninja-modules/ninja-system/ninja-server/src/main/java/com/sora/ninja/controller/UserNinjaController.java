package com.sora.ninja.controller;

import com.sora.domain.request.UserNinjaEntity;
import com.sora.ninja.service.UserNinjaService;
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
@RequestMapping("ninja/userninja")
public class UserNinjaController {
    @Autowired
    private UserNinjaService userNinjaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userNinjaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserNinjaEntity userNinja = userNinjaService.getById(id);

        return R.ok().put("userNinja", userNinja);
    }

    /**
     * <h2>增加用户的忍者</h2>
     * <p>
     *     判断用户碎片是否足够,记得减去忍者碎片,重新赋值用户碎片数量
     * </p>
     */
    @RequestMapping("/save/{userId}/{ninjaId}")
    public R saveNinja(@PathVariable("userId") Integer userId,@PathVariable("ninjaId") Integer ninjaId){
        return userNinjaService.saveNinja(userId, ninjaId);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserNinjaEntity userNinja){
		userNinjaService.updateById(userNinja);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userNinjaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
