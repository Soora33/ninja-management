package com.sora.remote;

import com.sora.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: NinjaRemoteServer
 * @description: TODO
 * @date: 2022/05/28
 * @author: 王致翔
 */
@FeignClient(value = "ninja-server",path = "/ninja/userninjachip")
public interface NinjaUserChipRemoteServer {

    /**
     * <h1>修改用户持有的忍者碎片个数</h1>
     * 根据用户id和忍者id修改 如果存在数量+1 不在插入数据
     */
    @RequestMapping("/update/{userId}/{ninjaId}")
    R updateNinjaChip(@PathVariable("userId") Integer userId, @PathVariable("ninjaId") Integer ninjaId);
}
