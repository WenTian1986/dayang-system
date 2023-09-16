package com.manong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.DepartmentLot;

/**
 * @ClassName DepartmentLotService
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/31 16:04
 **/
public interface DepartmentLotService extends IService<DepartmentLot> {

    /**
     * 查询车场是否已分配部门
     * @param id
     * @return
     */
    boolean hasInDepartment(Long id);
}
