package com.sora.factory;

import com.sora.remote.UserRemoteServer;
import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @className: UserFactory
 * @description: 用户熔断
 * @date: 2022/05/29
 * @author: Sora33
 */
@Component
@Log4j2
public class UserFactory implements FallbackFactory<UserRemoteServer> {
    @Override
    public UserRemoteServer create(Throwable cause) {
        return new UserRemoteServer() {
            @Override
            public R getUserByTel(String tel) {
                log.error("用户服务发生熔断!");
                return null;
            }

            @Override
            public R info(Integer id) {
                log.error("用户服务发生熔断!");
                return null;
            }
        };
    }
}
