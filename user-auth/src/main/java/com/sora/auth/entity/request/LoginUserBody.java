package com.sora.auth.entity.request;

import lombok.Data;

/**
 * @className: LoginUserBody
 * @description: 登录对象
 * @date: 2022/05/28
 * @author: Sora33
 */
@Data
public class LoginUserBody {
    private String tel;
    private String password;
}
