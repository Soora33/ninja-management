package com.sora.ninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: NinjaApplication
 * @description: TODO
 * @date: 2022/05/28
 * @author: 王致翔
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NinjaApplication {
    public static void main(String[] args) {
        SpringApplication.run(NinjaApplication.class);
    }
}
