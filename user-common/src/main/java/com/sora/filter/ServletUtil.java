package com.sora.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Sora33
 * @Classname ServletUtils
 * @Description TODO
 * @Date 2022/3/9 15:02
 */
@Log4j2
public class ServletUtil {

    public static Mono<Void> errorResponse(ServerWebExchange exchange, String msg) {
        log.error("[鉴权异常处理]请求路径:{} ，异常信息：{}", exchange.getRequest().getPath(),msg);
        ServerHttpResponse response = exchange.getResponse();
        //设置HTTP响应头状态
        response.setStatusCode(HttpStatus.OK);
        //设置HTTP响应头文本格式
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
        //定义响应内容
        DataBuffer dataBuffer = response.bufferFactory().wrap(msg.getBytes());
        //进行响应
        return response.writeWith(Mono.just(dataBuffer));
    }
}
