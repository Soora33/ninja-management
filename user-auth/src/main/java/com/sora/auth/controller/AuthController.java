package com.sora.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.sora.auth.entity.request.LoginUserBody;
import com.sora.auth.entity.response.UserVo;
import com.sora.remote.UserRemoteServer;
import com.sora.utils.JWTConstants;
import com.sora.utils.JwtUtils;
import com.sora.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @className: AuthController
 * @description: TODO
 * @date: 2022/05/28
 * @author: 王致翔
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRemoteServer userRemoteServer;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public R login(@RequestBody LoginUserBody loginUserBody) {
        R userByTel = userRemoteServer.getUserByTel(loginUserBody.getTel());
        Object tbUser = userByTel.get("tbUser");
        if (tbUser == null) {
            return R.error("手机号不存在");
        }
        UserVo userVo = JSONObject.parseObject(tbUser.toString(), UserVo.class);
        if (!userVo.getPassword().equals(loginUserBody.getPassword())) {
            return R.error("用户名或密码错误!");
        }
        // 创建token
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(JWTConstants.LOGIN_NAME, userVo.getName());
        hashMap.put(JWTConstants.USER_ID, userVo.getId());
        String token = JwtUtils.createToken(hashMap);
        // 存入redis
        redisTemplate.opsForValue().set(JWTConstants.TOKEN_PRE + userVo.getId(), JSONObject.toJSONString(userVo), 10, TimeUnit.MINUTES);
        return R.ok("登录成功")
                .put("token", token)
                .put("UserName", userVo.getName())
                .put("userId", userVo.getId())
                .put("vipLevel", userVo.getVipLevel())
                .put("priceTotal", userVo.getPriceTotal())
                .put("buyCount", userVo.getBuyCount());
    }
}
