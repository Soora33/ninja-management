package com.sora.ninja.controller;

import com.alibaba.fastjson.JSONObject;
import com.sora.domain.request.TbNinjaEntity;
import com.sora.ninja.service.TbNinjaService;
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
@RequestMapping("ninja/tbninja")
public class TbNinjaController {
    @Autowired
    private TbNinjaService tbNinjaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tbNinjaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 根据忍者信息获取忍者
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		TbNinjaEntity tbNinja = tbNinjaService.getById(id);

        return R.ok().put("tbNinja", JSONObject.toJSONString(tbNinja));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TbNinjaEntity tbNinja){
		tbNinjaService.save(tbNinja);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TbNinjaEntity tbNinja){
		tbNinjaService.updateById(tbNinja);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		tbNinjaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
