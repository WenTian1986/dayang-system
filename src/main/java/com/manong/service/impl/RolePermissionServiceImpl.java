package com.manong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.entity.RolePermission;
import com.manong.service.RolePermissionService;
import com.manong.dao.RolePermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 白衣卿相
* @description 针对表【sys_role_permission】的数据库操作Service实现
* @createDate 2023-06-15 14:54:18
*/
@Service
@Transactional
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




