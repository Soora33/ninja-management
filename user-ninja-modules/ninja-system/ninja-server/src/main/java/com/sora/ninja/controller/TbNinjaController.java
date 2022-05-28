package com.sora.ninja.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sora.domain.request.TbNinjaEntity;
import com.sora.ninja.service.TbNinjaService;
import com.sora.utils.PageUtils;
import com.sora.utils.R;



/**
 * 
 *
 * @author sora33
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
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		TbNinjaEntity tbNinja = tbNinjaService.getById(id);

        return R.ok().put("tbNinja", tbNinja);
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
