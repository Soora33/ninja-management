package com.sora.remote;

import com.sora.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: UserRemoteServer
 * @description: TODO
 * @date: 2022/05/28
 * @author: 王致翔
 */
@FeignClient(value = "sysuser-server", path = "/user/tbuser")
public interface UserRemoteServer {

    /**
     * 根据用户手机号获取用户
     * @Params tel
     */
    @RequestMapping("/infoByTel/{tel}")
    R getUserByTel(@PathVariable("tel") String tel);
}
