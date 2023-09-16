package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manong.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.vo.query.UserQueryVo;

import java.util.List;

/**
* @author 白衣卿相
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-06-15 14:53:35
*/
public interface UserService extends IService<User> {

    /**
     * @Description: 根据用户名查询用户信息
     * @param userName
     * @return com.manong.entity.User
     * @author zys
     * @date 2023/6/19 10:08
     */
    User findUserByUserName(String userName);

    /**
     * 分页查询用户信息
     * @param page
     * @param userQueryVo
     * @return
     */
    IPage<User> findUserListByPage(IPage<User> page, UserQueryVo userQueryVo);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 分配角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveUserRole(Long userId, List<Long> roleIds);

}
