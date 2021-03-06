package com.sora.remote;

import com.sora.domain.request.UserNinjaChipEntity;
import com.sora.factory.NinjaUserChipFactory;
import com.sora.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: NinjaRemoteServer
 * @description: TODO
 * @date: 2022/05/28
 * @author: Sora33
 */
@FeignClient(value = "ninja-server",path = "/ninja/userninjachip",fallbackFactory = NinjaUserChipFactory.class)
@Component
public interface NinjaUserChipRemoteServer {

    /**
     * <h1>修改用户持有的忍者碎片个数</h1>
     * 根据用户id和忍者id修改 如果存在数量+1 不在插入数据
     */
    @RequestMapping("/update/{userId}/{ninjaId}")
    R updateNinjaChip(@PathVariable("userId") Integer userId, @PathVariable("ninjaId") Integer ninjaId);

    /**
     * 根据用户id和忍者id获取对象
     */
    @RequestMapping("/info/{ninjaId}/{userId}")
    R getUserNinjaChip(@PathVariable("ninjaId") Integer ninjaId,@PathVariable("userId") Integer userId);

    /**
     * 修改
     */
    @RequestMapping("/update")
    R update(@RequestBody UserNinjaChipEntity userNinjaChip);

}
