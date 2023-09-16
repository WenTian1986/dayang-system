package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.ParkingRecords;
import com.manong.entity.Role;
import com.manong.vo.query.ParkingRecordsQueryVo;

/**
 * @ClassName parkingRecordsService
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/28 10:24
 **/
public interface ParkingRecordsService extends IService<ParkingRecords> {

    /**
     * 分页查询车辆记录列表
     * @param parkingRecordsQueryVo
     * @return
     */
    IPage<ParkingRecords> findParkingRecordslist(IPage<ParkingRecords> page, ParkingRecordsQueryVo parkingRecordsQueryVo);

}
