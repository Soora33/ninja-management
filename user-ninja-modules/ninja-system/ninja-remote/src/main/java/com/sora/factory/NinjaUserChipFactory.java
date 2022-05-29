package com.sora.factory;

import com.sora.domain.request.UserNinjaChipEntity;
import com.sora.remote.NinjaUserChipRemoteServer;
import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @className: NinjaUserChipFactory
 * @description: 用户忍者碎片熔断
 * @date: 2022/05/29
 * @author: Sora33
 */
@Component
@Log4j2
public class NinjaUserChipFactory  implements FallbackFactory<NinjaUserChipRemoteServer> {
    @Override
    public NinjaUserChipRemoteServer create(Throwable cause) {
        return new NinjaUserChipRemoteServer() {
            @Override
            public R updateNinjaChip(Integer userId, Integer ninjaId) {
                log.error("用户忍者碎片服务发生熔断!");
                return null;
            }

            @Override
            public R getUserNinjaChip(Integer ninjaId, Integer userId) {
                log.error("用户忍者碎片服务发生熔断!");
                return null;
            }

            @Override
            public R update(UserNinjaChipEntity userNinjaChip) {
                log.error("用户忍者碎片服务发生熔断!");
                return null;
            }
        };
    }
}
