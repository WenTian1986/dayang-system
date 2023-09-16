package com.manong.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manong.entity.ParkingLot;

import java.util.List;

/**
 * @ClassName ParkingLotMapper
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/26 15:12
 **/
public interface ParkingLotMapper extends BaseMapper<ParkingLot> {


    List<Long> findLotListByDeptId(Long departmentId);
}
