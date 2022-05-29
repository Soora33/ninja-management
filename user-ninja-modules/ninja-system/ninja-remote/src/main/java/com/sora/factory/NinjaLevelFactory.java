package com.sora.factory;

import com.sora.remote.NinjaLevelRemoteServer;
import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @className: NinjaLevelFactory
 * @description: 忍者等级熔断
 * @date: 2022/05/29
 * @author: Sora33
 */
@Component
@Log4j2
public class NinjaLevelFactory implements FallbackFactory<NinjaLevelRemoteServer> {
    @Override
    public NinjaLevelRemoteServer create(Throwable cause) {
        return new NinjaLevelRemoteServer() {
            @Override
            public R info(Integer id) {
                log.error("忍者等级服务发生熔断!");
                return null;
            }
        };
    }
}
