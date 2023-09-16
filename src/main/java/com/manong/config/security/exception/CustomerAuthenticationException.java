package com.manong.config.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName CustomerAuthenticationException
 * @Description: 自定义验证异常类
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/20 16:20
 **/
public class CustomerAuthenticationException extends AuthenticationException {

    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}
