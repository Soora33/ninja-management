package com.sora.remote;

import com.sora.factory.UserFactory;
import com.sora.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: UserRemoteServer
 * @description: 用户远程调用
 * @date: 2022/05/28
 * @author: Sora33
 */
@FeignClient(value = "sysuser-server", path = "/user/tbuser",fallbackFactory = UserFactory.class)
public interface UserRemoteServer {

    /**
     * 根据用户手机号获取用户
     * @Params tel
     */
    @RequestMapping("/infoByTel/{tel}")
    R getUserByTel(@PathVariable("tel") String tel);

    /**
     * 根据用户id获取用户
     */
    @RequestMapping("/info/{id}")
    R info(@PathVariable("id") Integer id);
}
