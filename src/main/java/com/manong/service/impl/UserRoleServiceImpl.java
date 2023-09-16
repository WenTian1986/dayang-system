package com.manong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.entity.UserRole;
import com.manong.service.UserRoleService;
import com.manong.dao.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 白衣卿相
* @description 针对表【sys_user_role】的数据库操作Service实现
* @createDate 2023-06-15 14:54:02
*/
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




