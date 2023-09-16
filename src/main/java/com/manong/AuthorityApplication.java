package com.manong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName AuthorityApplication
 * @Description: TODO
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/15 14:16
 **/
@MapperScan("com.manong.dao")
@SpringBootApplication
public class AuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class,args);
    }
}
