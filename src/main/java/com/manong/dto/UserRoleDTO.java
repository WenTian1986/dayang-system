package com.manong.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName UserRoleDTO
 * @Description: 用于给用户分配角色时保存选中的角色数据
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/25 10:42
 **/
@Data
public class UserRoleDTO {
    private Long userId;
    private List<Long> roleIds;
}
