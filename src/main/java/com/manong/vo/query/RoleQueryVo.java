package com.manong.vo.query;

import com.manong.entity.Role;
import lombok.Data;

/**
 * @ClassName RoleQueryVo
 * @Description: RoleQueryVo查询条件类
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/12 14:13
 **/
@Data
public class RoleQueryVo extends Role {

    private Long pageNo = 1L;//当前页码

    private Long pageSize = 10L;//每页显示数量

    private Long userId;//用户ID

}
