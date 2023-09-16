package com.manong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ParkingRecords
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/28 9:21
 **/
@TableName(value = "sys_parking_records")
@Data
public class ParkingRecords {

    /**
     * 记录编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 车牌号码
     */
    private String lpn;

    /**
     * 入场时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date entryTime;

    /**
     * 出场时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date exitTime;

    /**
     * 进车口
     */
    private String entry;

    /**
     * 出车口
     */
    private String export;

    /**
     * 车辆状态
     * 0-已出场 1-已入场 2-异常状态
     */
    private int state;

    /**
     * 是否删除(0-未删除 1-已删除)
     */
    private Integer isDelete;

    /**
     * 串行版本UID
     */
    @TableField(exist = false) // 因为表中不存在此字段，所以进行排除
    private static final long serialVersionUID = 1L;

}
