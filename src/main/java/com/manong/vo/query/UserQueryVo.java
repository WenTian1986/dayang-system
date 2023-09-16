package com.manong.vo.query;

import com.manong.entity.User;
import lombok.Data;

/**
 * @ClassName UserQueryVo
 * @Description: UserQueryVo条件类
 * @Author zys
 * @Version 1.0
 * @Date 2023/8/24 9:29
 **/
@Data
public class UserQueryVo extends User {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
    private Long userId;//用户ID
}
