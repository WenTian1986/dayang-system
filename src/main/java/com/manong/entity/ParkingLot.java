package com.manong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ParkingLot
 * @Description: 车场管理类
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/26 13:16
 **/
@TableName(value ="sys_parking_lot")
@Data
public class ParkingLot implements Serializable {

    /**
     * 车场编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 车场名称
     */
    private String parkingName;

    /**
     * 车场电话
     */
    private String phone;

    /**
     * 车场地址
     */
    private String address;

    /**
     * 车场gps位置
     * 后期迭代处理
     */
//    private String gpsAdd;

    /**
     * 总车位数
     * 后期迭代处理
     */
//    private int totalNum;

    /**
     * 大车位总车位数
     * 后期迭代处理
     */
//    private int bigTotalNum;

    /**
     * 小车位总车位数
     * 后期迭代处理
     */
//    private int smallTotalNum;

    /**
     * 大充电车位总车位数
     * 后期迭代处理
     */
//    private int bigChargeTotalNum;

    /**
     * 小充电车位总车位数
     * 后期迭代处理
     */
//    private int smallChargeTotalNum;

    /**
     * 大车位剩余车位数
     * 后期迭代处理
     */
//    private int bigEmptyNum;

    /**
     * 小车位剩余车位数
     * 后期迭代处理
     */
//    private int smallEmptyNum;

    /**
     * 大充电车位剩余车位数
     * 后期迭代处理
     */
//    private int bigChargeEmptyNum;

    /**
     * 小充电车位剩余车位数
     * 后期迭代处理
     */
//    private int smallChargeEmptyNum;

    /**
     * 车场管理员id
     * 后期迭代处理
     */
//    private long adminId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     * 后期迭代处理
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 车场状态
     * 后期迭代处理
     * 0-未接入 1-已接入 2-已停用
     */
    private int parkState;

    /**
     * 车场状态
     * 0-非经营性车场 1-经营性车场 2-医院 3-办公园区 4-酒店
     * 后期迭代处理
     */
//    private int parkType;

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