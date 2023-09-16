package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.ParkingLotMapper;

import com.manong.entity.ParkingLot;;

import com.manong.entity.User;
import com.manong.service.ParkingLotService;
import com.manong.vo.query.ParkingLotQueryVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.util.List;

/**
 * @ClassName ParkingLotServiceImpl
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/26 15:10
 **/
@Service
@Transactional
public class ParkingLotServiceImpl extends ServiceImpl<ParkingLotMapper, ParkingLot> implements ParkingLotService {

    /**
     * 查询车场列表
     * @return List<ParkingLot>
     */
    @Override
    public List<ParkingLot> getAllParkingLotList() {
        return baseMapper.selectList(null);
    }

    /**
     * 分页查询车场列表
     * @param page
     * @param parkingLotQueryVo
     * @return page
     */
    @Override
    public IPage<ParkingLot> findParkingLotList(IPage<ParkingLot> page, ParkingLotQueryVo parkingLotQueryVo) {
        //创建条件构造器
        LambdaQueryWrapper<ParkingLot> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        queryWrapper.like(!ObjectUtils.isEmpty(parkingLotQueryVo.getParkingName()),
                ParkingLot::getParkingName, parkingLotQueryVo.getParkingName());
        //排序
        queryWrapper.orderByAsc(ParkingLot::getCreateTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 通过部门分页查询车场列表
     * @param page
     * @param parkingLotQueryVo
     * @return page
     */
    @Override
    public IPage<ParkingLot> findParkingLotListByDeptId(IPage<ParkingLot> page, ParkingLotQueryVo parkingLotQueryVo) {

        // 获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        //若查询条件部门编号为空且用户不是管理员，则按照用户所在部门编号查询
        if (parkingLotQueryVo.getDepartmentId() == null && user.getIsAdmin() != 1){
            parkingLotQueryVo.setDepartmentId(user.getDepartmentId());
        }
        //判断部门编号是否为空且不等于0，否则表示查询全部车场
        List<Long> parkingLotIds = null;
        if (!ObjectUtils.isEmpty(parkingLotQueryVo.getDepartmentId()) && parkingLotQueryVo.getDepartmentId() != 0 ) {
            parkingLotIds = baseMapper.findLotListByDeptId(parkingLotQueryVo.getDepartmentId());
            //判断该部门下是否存在车场，如果不存在，车场id列表添加0（不存在id=0的车场，用于下面分页条件查询）
            if (parkingLotIds.size() == 0){
                parkingLotIds.add(0l);
            }
        }
        //创建条件构造器
        LambdaQueryWrapper<ParkingLot> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        queryWrapper.like(!ObjectUtils.isEmpty(parkingLotQueryVo.getParkingName()),
                ParkingLot::getParkingName, parkingLotQueryVo.getParkingName())
                .in(!ObjectUtils.isEmpty(parkingLotIds), ParkingLot::getId,parkingLotIds);
        //排序
        queryWrapper.orderByAsc(ParkingLot::getCreateTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 通过车场名称查找车场
     * @param parkingName
     * @return ParkingLot
     */
    @Override
    public ParkingLot findLotByName(String parkingName) {
        //创建条件构造器
        LambdaQueryWrapper<ParkingLot> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        queryWrapper.eq(!ObjectUtils.isEmpty(parkingName), ParkingLot::getParkingName, parkingName);
        //返回查询结果
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 删除车场
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        //删除该用户对应的角色信息
        return baseMapper.deleteById(id)>0;
    }
}
