package com.sora.auth.entity.request;

import lombok.Data;

/**
 * @className: LoginUserBody
 * @description: TODO
 * @date: 2022/05/28
 * @author: 王致翔
 */
@Data
public class LoginUserBody {
    private String tel;
    private String password;
}
