package com.manong.vo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manong.entity.ParkingRecords;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName ParkingRecordsVo
 * @Description: 车场记录查询类
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/28 9:38
 **/
@Data
public class ParkingRecordsQueryVo extends ParkingRecords {

    private Long pageNo = 1L;//当前页码

    private Long pageSize = 10L;//每页显示数量


//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime; //查询开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime; //查询结束时间
}
