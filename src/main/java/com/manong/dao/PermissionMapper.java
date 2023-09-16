package com.manong.dao;

import com.manong.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 白衣卿相
* @description 针对表【sys_permission】的数据库操作Mapper
* @createDate 2023-06-15 14:54:31
* @Entity com.manong.entity.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * @Description: 根据用户ID查询权限列表
     * @param userId
     * @return List<Permission>
     * @author zys
     * @date 2023/6/20 9:37
     */
    List<Permission> findPermissionListByUserId(long userId);

    /**
     * 根据角色ID查询权限列表
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(Long roleId);

}




