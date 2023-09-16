package com.manong.controller;

import com.manong.config.redis.RedisService;
import com.manong.entity.Permission;
import com.manong.entity.User;
import com.manong.entity.UserInfo;
import com.manong.utils.JwtUtils;
import com.manong.utils.MenuTree;
import com.manong.utils.Result;
import com.manong.vo.RouterVo;
import com.manong.vo.TokenVo;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName SysUserControlle
 * @Description: SysUserController控制器类
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/21 10:04
 **/
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private RedisService redisService;

    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {

        //从header中获取前端提交的token
        String token = request.getHeader("token");
        //如果header中没有token，则从参数中获取
        if (ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取身份信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //重新生成token
        String reToken = "";
        //验证原来的token是否合法
        if (jwtUtils.validateToken(token, userDetails)) {
            //生成新的token
            reToken = jwtUtils.refreshToken(token);
        }
        //获取本次token的到期时间，交给前端做判断
        long expireTime = Jwts.parser()
                .setSigningKey(jwtUtils.getSecret())
                .parseClaimsJws(reToken.replace("jwt_", ""))
                .getBody().getExpiration().getTime();
        //清除原来的token信息
        String oldTokenKey = "token_" + token;
        redisService.del(oldTokenKey);
        //存储新的token
        String newTokenKey = "token_" + reToken;
        redisService.set(newTokenKey, reToken, jwtUtils.getExpiration() / 1000);
        //创建TokenVo对象
        TokenVo tokenVo = new TokenVo(expireTime, reToken);
        //返回数据
        return Result.ok(tokenVo).message("token生成成功");
    }

    @GetMapping("/getInfo")
    public Result getInfo() {

        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //判断authentication对象是否为空
        if (authentication == null) {
            return Result.error().message("用户信息查询失败");
        }

        //获取用户信息
        User user = (User) authentication.getPrincipal();

        //用户权限集合
        List<Permission> permissionList = user.getPermissionList();

        //获取角色权限编码字段
        Object[] roles = permissionList.stream()
                        .filter(Objects::nonNull)
                        .map(Permission::getCode).toArray();

        //创建用户信息对象
        UserInfo userInfo = new UserInfo(user.getId(), user.getNickName(), user.getAvatar(), null, roles);

        //返回数据
        return Result.ok(userInfo).message("用户信息查询成功");
    }

    /**
     * 获取菜单数据
     * @return
     */
    @GetMapping("/getMenuList")
    public Result getMenuList() {

        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户信息
        User user = (User) authentication.getPrincipal();
        //获取当前用户的权限信息
        List<Permission> permissionList = user.getPermissionList();
        //筛选当前用户拥有的目录和菜单数据
        List<Permission> collect = permissionList.stream()
                .filter(item -> item != null && item.getType() !=2) //0-目录 1-菜单 2-按钮
                .collect(Collectors.toList());
        //生成路由数据
        List<RouterVo> routerVoList = MenuTree.makeRouter(collect, 0L);
        //返回数据
        return Result.ok(routerVoList).message("菜单数据获取成功");
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {

        //从header中获取前端提交的token
        String token = request.getHeader("token");
        //如果header中没有token，则从参数中获取
        if (ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        //获取用户相关信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //如果用户信息不为空，执行退出业务逻辑
        if (authentication != null) {
            //清空Security上下文用户信息
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            //清空redis里面的token
            String key = "token_" + token;
            redisService.del(key);
            return Result.ok().message("用户退出成功");
        }
        return Result.error().message("用户退出失败");
    }
}