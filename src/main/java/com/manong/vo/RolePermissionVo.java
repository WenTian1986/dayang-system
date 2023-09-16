package com.manong.vo;

import com.manong.entity.Permission;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RolePermissionVo
 * @Description: 用于封装权限菜单数据回显
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/22 14:09
 **/
@Data
public class RolePermissionVo {

    /**
     * 菜单数据
     */
    private List<Permission> permissionList = new ArrayList<Permission>();

    /**
     * 该角色原有分配的菜单数据
     */
    private Object [] checkList;

}
