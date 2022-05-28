package com.sora.user.controller;

import com.sora.user.entity.LevelConsultEntity;
import com.sora.user.service.LevelConsultService;
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
@RequestMapping("user/levelconsult")
public class LevelConsultController {
    @Autowired
    private LevelConsultService levelConsultService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = levelConsultService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		LevelConsultEntity levelConsult = levelConsultService.getById(id);

        return R.ok().put("levelConsult", levelConsult);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LevelConsultEntity levelConsult){
		levelConsultService.save(levelConsult);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LevelConsultEntity levelConsult){
		levelConsultService.updateById(levelConsult);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		levelConsultService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
