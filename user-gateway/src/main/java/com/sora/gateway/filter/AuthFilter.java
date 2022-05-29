package com.sora.gateway.filter;

import com.sora.filter.ServletUtil;
import com.sora.utils.JWTConstants;
import com.sora.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.TreeSet;

/**
 * @className: AuthFilter
 * @description: 网关过滤
 * @date: 2022/05/29
 * @author: Sora33
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 白名单
     */
    private static final TreeSet<String> pathSet = new TreeSet<String>() {{
        add("/api/auth/login");
    }};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求路径
        String path = request.getURI().getPath();

        // 白名单中放行
        if (pathSet.contains(path)) {
            return chain.filter(exchange);
        }

        // 获取token
        List<String> token = request.getHeaders().get("token");
        if (token == null || token.size() == 0) {
            return ServletUtil.errorResponse(exchange,"未携带token");
        }
        String tokenValue = token.get(0);
        if (tokenValue != null) {
            String userId = JwtUtils.getUserId(tokenValue);
            // 从redis获取用户信息
            Boolean hasKey = redisTemplate.hasKey(JWTConstants.TOKEN_PRE + userId);
            if (!hasKey) {
                return ServletUtil.errorResponse(exchange,"token已过期");
            }
        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
