package com.manong.service;

import com.manong.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.vo.query.DepartmentQueryVo;

import java.util.List;

/**
* @author 白衣卿相
* @description 针对表【sys_department】的数据库操作Service
* @createDate 2023-06-15 14:54:35
*/
public interface DepartmentService extends IService<Department> {
    /**
     * 查询部门列表
     * @param departmentQueryVo
     * @return
     */
    List<Department> findDepartmentList(DepartmentQueryVo departmentQueryVo);

    /**
     * 查询上级部门列表
     * @return
     */
    List<Department> findParentDepartment();

    /**
     * 判断部门下是否有子部门
     * @param id
     * @return
     */
    boolean hasChildrenOfDepartment(Long id);

    /**
     * 判断部门下是否存在用户
     * @param id
     * @return
     */
    boolean hasUserOfDepartment(Long id);

    /**
     * 判断部门下是否存在车场
     * @param id
     * @return
     */
    boolean hasParkLotOfDepartment(Long id);

    /**
     * 通过ID删除部门下车场
     * @param LotId,deptId
     * @return
     */
    boolean removeLotById(Long LotId, Long deptId);
}
