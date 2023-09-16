package com.manong.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginResult
 * @Description: token返回的登录数据信息类
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/19 14:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {

    //用户编号
    private Long id;
    //状态码
    private int code;
    //token令牌
    private String token;
    //token过期时间
    private Long expireTime;

}
