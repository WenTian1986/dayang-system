package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.entity.User;
import com.manong.service.UserService;
import com.manong.dao.UserMapper;
import com.manong.vo.query.UserQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
* @author 白衣卿相
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-06-15 14:53:35
*/
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    /**
     * @Description: 根据用户名查询用户信息
     * @param userName
     * @return com.manong.entity.User
     * @author zys
     * @date 2023/6/19 10:08
     */
    @Override
    public User findUserByUserName(String userName) {
        //创建条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        //创建查询条件
        queryWrapper.eq("username", userName);
        //返回查询记录
        return baseMapper.selectOne(queryWrapper);
    }

    /**
    * 分页查询用户信息
    * @param page
    * @param userQueryVo
    * @return
    */
    @Override
    public IPage<User> findUserListByPage(IPage<User> page, UserQueryVo userQueryVo) {

        //创建条件构造器对象
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //部门编号
        queryWrapper.eq(!ObjectUtils.isEmpty(userQueryVo.getDepartmentId()),User::getDepartmentId,userQueryVo.getDepartmentId());
        //用户名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getUsername()),User::getUsername,userQueryVo.getUsername());
        //真实姓名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getRealName()),User::getRealName,userQueryVo.getRealName());
        //电话
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getPhone()),User::getPhone,userQueryVo.getPhone());
        //查询并返回数据
        return baseMapper.selectPage(page,queryWrapper);

    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteById(Long id) {
        //查询
        User user = baseMapper.selectById(id);
        //删除用户角色关系
        baseMapper.deleteUserRole(id);
        //删除用户
        if (baseMapper.deleteById(id) > 0) {
            //判断用户是否存在
//            if (user != null && !ObjectUtils.isEmpty(user.getAvatar())
//                    && !user.getAvatar().equals(SystemConstants.DEFAULT_AVATAR)) {
//                //删除文件
//                fileService.deleteFile(user.getAvatar());
//            }
            return true;
        }
        return false;
    }

    /**
     * 分配角色
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveUserRole(Long userId, List<Long> roleIds) {
        //删除该用户对应的角色信息
        baseMapper.deleteUserRole(userId);
        //保存用户角色信息
        return baseMapper.saveUserRole(userId,roleIds)>0;
    }
}



