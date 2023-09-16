package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manong.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.vo.query.RoleQueryVo;

import java.util.List;

/**
* @author 白衣卿相
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2023-06-15 14:54:27
*/
public interface RoleService extends IService<Role> {

    /**
     * 根据用户查询角色列表
     * @param page
     * @param roleQueryVo
     * @return
     */
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 检查角色是否被使用
     * @param id
     * @return
     */
    boolean hasRoleCount(Long id);

    /**
     * 删除角色
     * @param id
     * @return
     */
    boolean deleteRoleById(Long id);

    /**
     * 根据用户ID查询该用户拥有的角色ID
     * @param userId
     * @return
     */
    List<Long> findRoleIdByUserId(Long userId);
}

