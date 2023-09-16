package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.ParkingLot;
import com.manong.vo.query.ParkingLotQueryVo;

import java.util.List;

/**
 * @ClassName parkingService
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/26 15:08
 **/
public interface ParkingLotService extends IService<ParkingLot> {

    /**
     * 查询车场列表
     * @return List<ParkingLot>
     */
    List<ParkingLot> getAllParkingLotList();


    /**
     * 分页查询车场列表
     * @param page
     * @param parkingLotQueryVo
     * @return page
     */
    IPage<ParkingLot> findParkingLotList(IPage<ParkingLot> page, ParkingLotQueryVo parkingLotQueryVo);

    /**
     * 通过部门分页查询车场列表
     * @param page
     * @param parkingLotQueryVo
     * @return page
     */
    IPage<ParkingLot> findParkingLotListByDeptId(IPage<ParkingLot> page, ParkingLotQueryVo parkingLotQueryVo);

    /**
     * 通过车场名称查找车场
     * @param parkingName
     * @return ParkingLot
     */
    ParkingLot findLotByName(String parkingName);

    /**
     * 删除车场
     * @param id
     * @return
     */
    boolean deleteById(Long id);
}
