package com.manong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserInfo
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/21 15:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Long id;//用户ID
    private String name;//用户名称
    private String avatar;//头像
    private String introduction;//介绍
    private Object[] roles;//角色权限集合
}
