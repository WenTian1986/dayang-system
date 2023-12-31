package com.manong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 
 * @TableName sys_permission
 */
@TableName(value ="sys_permission")
@Data
public class Permission implements Serializable {

    /**
     * 权限编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    private String label;

    /**
     * 父权限ID
     */
    private Long parentId;

    /**
     * 父权限名称
     */
    private String parentName;

    /**
     * 授权标识符
     */
    private String code;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由名称
     */
    private String name;

    /**
     * 授权路径
     */
    private String url;

    /**
     * 权限类型(0-目录 1-菜单 2-按钮)
     */
    private Integer type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Integer isDelete;

    /**
     * 串行版本UID
     */
    @TableField(exist = false) // 因为表中不存在此字段，所以进行排除
    private static final long serialVersionUID = 1L;

    /**
     * 子菜单列表
     */
    @JsonInclude(JsonInclude.Include.NON_NULL) //属性值为null不进行序列化操作
    @TableField(exist = false) // 因为表中不存在此字段，所以进行排除
    private List<Permission> children = new ArrayList<>();

    /**
     * 用于前端判断是菜单、目录或按钮
     */
    @TableField(exist = false) // 因为表中不存在此字段，所以进行排除
    private String value;

    /**
     * 是否展开
     */
    @TableField(exist = false) // 因为表中不存在此字段，所以进行排除
    private Boolean open;

}