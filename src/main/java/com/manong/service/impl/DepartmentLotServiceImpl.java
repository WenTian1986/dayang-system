package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.DepartmentLotMapper;
import com.manong.entity.DepartmentLot;
import com.manong.service.DepartmentLotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName DepartmentLotServiceImol
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/31 16:05
 **/
@Service
@Transactional
public class DepartmentLotServiceImpl extends ServiceImpl<DepartmentLotMapper, DepartmentLot> implements DepartmentLotService {

    /**
     * 查询车场是否已分配部门
     * @param id
     * @return
     */
    @Override
    public boolean hasInDepartment(Long id) {
        //创建条件构造器
        LambdaQueryWrapper<DepartmentLot> queryWrapper = new LambdaQueryWrapper();
        //查询条件
        queryWrapper.eq(DepartmentLot::getLotId,id);
        //返回结果
        return baseMapper.selectCount(queryWrapper)>0;
    }
}
