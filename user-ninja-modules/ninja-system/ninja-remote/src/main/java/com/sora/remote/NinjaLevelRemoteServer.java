package com.sora.remote;

import com.sora.factory.NinjaLevelFactory;
import com.sora.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: NinjLevelRemoteServer
 * @description: TODO
 * @date: 2022/05/29
 * @author: Sora33
 */
@FeignClient(value = "ninja-server",path = "/ninja/tblevel",fallbackFactory = NinjaLevelFactory.class)
@Component
public interface NinjaLevelRemoteServer {

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    R info(@PathVariable("id") Integer id);
}
