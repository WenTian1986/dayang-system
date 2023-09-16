package com.manong.dao;

import com.manong.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 白衣卿相
* @description 针对表【sys_role】的数据库操作Mapper
* @createDate 2023-06-15 14:54:27
* @Entity com.manong.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 删除角色权限关系
     * @param roleId
     */
    void deleteRolePermission(Long roleId);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    int saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 查询角色数量
     * @param id
     * @return
     */
    int getRoleCountByRoleId(Long id);

    /**
     * 根据用户ID查询该用户拥有的角色ID
     * @param userId
     * @return
     */
    @Select("select role_id from `sys_user_role` where user_id = #{userId}")
    List<Long> findRoleIdByUserId(Long userId);
}




