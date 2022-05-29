package com.sora.factory;

import com.sora.remote.NinjaRemoteServer;
import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @className: NinjaFactory
 * @description: 忍者熔断
 * @date: 2022/05/29
 * @author: Sora33
 */
@Component
@Log4j2
public class NinjaFactory implements FallbackFactory<NinjaRemoteServer> {
    @Override
    public NinjaRemoteServer create(Throwable cause) {
        return new NinjaRemoteServer() {
            @Override
            public R info(Integer id) {
                log.error("忍者服务发生熔断!");
                return null;
            }
        };
    }
}
