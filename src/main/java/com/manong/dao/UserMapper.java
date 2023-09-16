package com.manong.dao;

import com.manong.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
* @author 白衣卿相
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2023-06-15 14:53:35
* @Entity com.manong.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<User> getALL();

    /**
     * 删除用户角色关系
     * @param userId
     * @return
     */
    @Delete("delete from sys_user_role where user_id=#{userId}")
    int deleteUserRole(Long userId);

    /**
     * 保存用户角色关系
     * @param userId
     * @param roleIds
     * @return
     */
    int saveUserRole(Long userId, List<Long> roleIds);
}




