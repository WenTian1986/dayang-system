package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.DepartmentLotMapper;
import com.manong.dao.UserMapper;
import com.manong.entity.Department;
import com.manong.entity.DepartmentLot;
import com.manong.entity.User;
import com.manong.service.DepartmentService;
import com.manong.dao.DepartmentMapper;
import com.manong.utils.DepartmentTree;
import com.manong.vo.query.DepartmentQueryVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 白衣卿相
 * @description 针对表【sys_department】的数据库操作Service实现
 * @createDate 2023-06-15 14:54:35
 */
@Service
@Transactional
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DepartmentLotMapper departmentLotMapper;

    /**
     * 查询部门列表
     * @param departmentQueryVo
     * @return
     */
    @Override
    public List<Department> findDepartmentList(DepartmentQueryVo departmentQueryVo) {

        //创建条件构造器对象
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        //部门名称
        queryWrapper.like(!ObjectUtils.isEmpty(departmentQueryVo.getDepartmentName()), "department_name", departmentQueryVo.getDepartmentName());
        //排序
        queryWrapper.orderByAsc("order_num");
        //查询部门列表
        List<Department> departmentList = baseMapper.selectList(queryWrapper);
        // 获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        //生成部门树
        List<Department> departmentTree = DepartmentTree.makeDepartmentTree(departmentList, user.getDepartmentId());
        return departmentTree;
    }

    /**
     * @param null
     * @return List<Department>
     * @Description: 查询上级部门列表
     * @author zys
     * @date 2023/8/5 14:10
     */
    @Override
    public List<Department> findParentDepartment() {
        //创建条件构造器对象
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();
        //排序
        queryWrapper.orderByAsc("order_num");
        //查询部门列表
        List<Department> departmentList = baseMapper.selectList(queryWrapper);
        //创建部门对象
        Department department = new Department();
        department.setId(0L);
        department.setDepartmentName("顶级部门");
        department.setPid(-1L);
        departmentList.add(department);
        //生成部门树列表
        List<Department> departmentTree = DepartmentTree.makeDepartmentTree(departmentList, -1L);
        //返回部门列表
        return departmentTree;
    }

    /**
     * @param id
     * @return boolean
     * @Description: 查询部门下是否有子部门
     * @author zys
     * @date 2023/8/5 14:10
     */
    @Override
    public boolean hasChildrenOfDepartment(Long id) {
        //创建条件构造器对象
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();
        queryWrapper.eq("pid", id);
        //如果数量大于0，表示存在
        if (baseMapper.selectCount(queryWrapper) > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id
     * @return boolean
     * @Description: 查询部门下是否有用户
     * @author zys
     * @date 2023/8/5 14:10
     */
    @Override
    public boolean hasUserOfDepartment(Long id) {
        //创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("department_id", id);
        //如果数量大于0，表示存在
        if (userMapper.selectCount(queryWrapper) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断部门下是否存在车场
     * @param id
     * @return
     */
    @Override
    public boolean hasParkLotOfDepartment(Long id) {
        //创建条件构造器对象
        LambdaQueryWrapper<DepartmentLot> queryWrapper = new LambdaQueryWrapper<DepartmentLot>();
        queryWrapper.eq(DepartmentLot::getDeptId, id);
        //如果数量大于0，表示存在
        if (departmentLotMapper.selectCount(queryWrapper) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过ID删除部门下车场
     * @param LotId,deptId
     * @return
     */
    @Override
    public boolean removeLotById(Long LotId, Long deptId) {
        //创建条件构造器对象
        LambdaQueryWrapper<DepartmentLot> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DepartmentLot::getLotId, LotId)
                .eq(DepartmentLot::getDeptId, deptId);
        //如果数量大于0，表示存在
        if (departmentLotMapper.delete(queryWrapper) > 0) {
            return true;
        }
        return false;
    }
}


