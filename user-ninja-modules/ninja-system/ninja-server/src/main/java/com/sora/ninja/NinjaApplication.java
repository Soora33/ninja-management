package com.sora.ninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: NinjaApplication
 * @description: TODO
 * @date: 2022/05/28
 * @author: Sora33
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"com.sora.**"})
@EnableFeignClients(basePackages = {"com.sora.**"})
public class NinjaApplication {
    public static void main(String[] args) {
        SpringApplication.run(NinjaApplication.class);
    }
}
