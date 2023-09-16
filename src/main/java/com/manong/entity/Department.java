package com.manong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName sys_department
 */
@TableName(value ="sys_department")
@Data
public class Department implements Serializable {
    /**
     * 部门编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门电话
     */
    private String phone;

    /**
     * 部门地址
     */
    private String address;

    /**
     * 所属部门编号
     */
    private Long pid;

    /**
     * 所属部门名称
     */
    private String parentName;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否删除(0-未删除 1-已删除)
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 是否展开
     */
    @TableField(exist = false)
    private Boolean open;

    /**
     * 子部门
     */
    @TableField(exist = false)
    private List<Department> children = new ArrayList<>();

}