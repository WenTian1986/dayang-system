package com.manong.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.manong.config.security.exception.CustomerAuthenticationException;
import com.manong.utils.Result;
import com.manong.utils.ResultCode;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName LoginFailureHandler
 * @Description: 用户认证失败处理类
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/19 10:30
 **/
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //设置客户端响应编码格式
        response.setContentType("application/json;charset=UTF-8");
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //定义提示信息变量
        String message = null;
        //定义错误编码变量
        int code = ResultCode.ERROR;
        //判断异常类型
        if (exception instanceof AccountExpiredException) {
            message = "账户过期,登录失败！";
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误,登录失败！";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "密码过期,登录失败！";
        } else if (exception instanceof DisabledException) {
            message = "账户被禁用,登录失败！";
        } else if (exception instanceof LockedException) {
            message = "账户被锁,登录失败！";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            message = "账户不存在,登录失败！";
        } else if (exception instanceof CustomerAuthenticationException) {
            message = exception.getMessage();
            code = ResultCode.NO_LOGIN;
        } else {
            message = "登录失败！";
        }
        //将错误信息转换成JSON
        String result = JSON.toJSONString(Result.error().code(code).message(message));
        //输出数据
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }
}
