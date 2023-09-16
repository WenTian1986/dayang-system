package com.manong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DepartmentLot
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/31 15:56
 **/
@TableName(value ="sys_dept_lot")
@Data
public class DepartmentLot implements Serializable {

    /**
     * 部门编号
     */
    private Long deptId;

    /**
     * 车场编号
     */
    private Long LotId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
