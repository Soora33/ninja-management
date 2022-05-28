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
@FeignClient(value = "ninja-server",path = "/ninja/tbninja")
public interface NinjaRemoteServer {

    /**
     * 根据忍者信息获取忍者
     */
    @RequestMapping("/info/{id}")
    R info(@PathVariable("id") Integer id);
}
