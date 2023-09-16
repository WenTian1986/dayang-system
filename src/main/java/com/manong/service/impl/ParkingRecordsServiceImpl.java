package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.ParkingRecordsMapper;
import com.manong.entity.ParkingRecords;
import com.manong.service.ParkingRecordsService;
import com.manong.vo.query.ParkingRecordsQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName parkingRecordsServiceImpl
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/28 10:25
 **/
@Service
@Transactional
public class ParkingRecordsServiceImpl extends ServiceImpl<ParkingRecordsMapper, ParkingRecords> implements ParkingRecordsService {

    /**
     * 分页查询车辆记录列表
     *
     * @param parkingRecordsQueryVo
     * @return
     */
    @Override
    public IPage<ParkingRecords> findParkingRecordslist(IPage<ParkingRecords> page, ParkingRecordsQueryVo parkingRecordsQueryVo) {

        //创建条件构造器
        LambdaQueryWrapper<ParkingRecords> queryWrapper = new LambdaQueryWrapper<>();
        //明确车场，处理相关业务，代码后期迭代升级!!!!!!
        //查询条件
        queryWrapper.like(!ObjectUtils.isEmpty(parkingRecordsQueryVo.getLpn()), ParkingRecords::getLpn, parkingRecordsQueryVo.getLpn())
                .ge(!ObjectUtils.isEmpty(parkingRecordsQueryVo.getStartTime()), ParkingRecords::getEntryTime, parkingRecordsQueryVo.getStartTime())
                .le(!ObjectUtils.isEmpty(parkingRecordsQueryVo.getEndTime()), ParkingRecords::getEntryTime, parkingRecordsQueryVo.getEndTime());
        //排序
        queryWrapper.orderByAsc(ParkingRecords::getEntryTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

}