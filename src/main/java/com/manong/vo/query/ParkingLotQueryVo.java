package com.manong.vo.query;

import com.manong.entity.ParkingLot;
import lombok.Data;

/**
 * @ClassName parkingLotQueryVo
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/29 15:21
 **/
@Data
public class ParkingLotQueryVo extends ParkingLot {

    private Long pageNo = 1L;//当前页码

    private Long pageSize = 10L;//每页显示数量

    private Long userId;

    private Long departmentId;

}
