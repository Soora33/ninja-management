package com.sora.ninja.controller;

import com.alibaba.fastjson.JSONObject;
import com.sora.domain.request.TbLevelEntity;
import com.sora.ninja.service.TbLevelService;
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
@RequestMapping("ninja/tblevel")
public class TbLevelController {
    @Autowired
    private TbLevelService tbLevelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tbLevelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		TbLevelEntity tbLevel = tbLevelService.getById(id);

        return R.ok().put("tbLevel", JSONObject.toJSONString(tbLevel));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TbLevelEntity tbLevel){
		tbLevelService.save(tbLevel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TbLevelEntity tbLevel){
		tbLevelService.updateById(tbLevel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		tbLevelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
