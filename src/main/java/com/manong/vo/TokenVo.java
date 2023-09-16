package com.manong.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TokenVo
 * @Description: 保存Token信息的TokenVo类
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/21 10:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVo {

    //过期时间
    private Long expireTime;
    //token
    private String token;

}
