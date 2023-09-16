package com.manong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manong.entity.ParkingLot;
import com.manong.entity.ParkingRecords;
import com.manong.service.DepartmentLotService;
import com.manong.service.ParkingLotService;
import com.manong.service.ParkingRecordsService;
import com.manong.utils.Result;
import com.manong.vo.query.ParkingLotQueryVo;
import com.manong.vo.query.ParkingRecordsQueryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ParkingController
 * @Description: 停车场控制类
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/26 15:06
 **/
@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Resource
    private ParkingLotService parkingLotService;

    @Resource
    private ParkingRecordsService parkingRecordsService;

    @Resource
    private DepartmentLotService departmentLotService;

    /**
     * 添加车场
     * @param parkingLot
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody ParkingLot parkingLot){
        parkingLot.setCreateTime(new Date());
        // 添加创建时间
        parkingLot.setCreateTime(new Date());
        if(parkingLotService.save(parkingLot)){
            return Result.ok().message("车场添加成功");
        }
        return Result.error().message("车场添加失败");
    }

    /**
     * 修改车场
     * @param parkingLot
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody ParkingLot parkingLot) {
        parkingLot.setUpdateTime(new Date());
        //查询用户
        ParkingLot item = parkingLotService.findLotByName(parkingLot.getParkingName());
        //判断对象是否为空,且查询到的车场ID不等于当前编辑的车场ID，表示该名称被占用
        if (item != null && item.getId() != parkingLot.getId()) {
            return Result.error().message("修改后的车场名称已被占用！");
        }
        //调用修改用户信息的方法
        if (parkingLotService.updateById(parkingLot)) {
            return Result.ok().message("车场修改成功");
        }
        return Result.error().message("车场修改失败");
    }

    /**
     * 删除车场
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        //调用删除用户信息的方法
        if (parkingLotService.deleteById(id)) {
            return Result.ok().message("车场删除成功");
        }
        return Result.error().message("车场删除失败");
    }

    /**
     * 查询全部车场列表
     * @param parkingLotQueryVo
     * @return
     */
    @GetMapping("/parkingLot/list")
    public Result getParkingLotList(ParkingLotQueryVo parkingLotQueryVo) {
        //调用分页查询方法
        List<ParkingLot> lots = parkingLotService.getAllParkingLotList();
        //返回数据
        return Result.ok(lots);
    }

    /**
     * 分页查询车场列表
     * @param parkingLotQueryVo
     * @return
     */
    @GetMapping("/parkingLot/page")
    public Result getParkingLotPage(ParkingLotQueryVo parkingLotQueryVo) {
        //创建分页对象
        IPage<ParkingLot> page = new Page<>(parkingLotQueryVo.getPageNo(), parkingLotQueryVo.getPageSize());
        //调用分页查询方法
        parkingLotService.findParkingLotListByDeptId(page, parkingLotQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 分页查询车辆记录列表
     * @param parkingRecordsQueryVo
     * @return
     */
    @GetMapping("/records/list")
    public Result getParkingRecordslist(ParkingRecordsQueryVo parkingRecordsQueryVo) {
        //创建分页对象
        IPage<ParkingRecords> page = new Page<>(parkingRecordsQueryVo.getPageNo(), parkingRecordsQueryVo.getPageSize());
        //调用分页查询方法
        parkingRecordsService.findParkingRecordslist(page, parkingRecordsQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 查询车场是否已分配部门
     * @param id
     * @return
     */
    @GetMapping("/check/{id}")
    public Result check(@PathVariable Long id){
        if(departmentLotService.hasInDepartment (id)){
            return Result.exist().message("该车场已分配部门，无法删除");
        };
        return Result.ok();
    }
}
