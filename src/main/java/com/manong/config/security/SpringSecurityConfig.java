package com.manong.config.security;

import com.manong.config.security.filter.CheckTokenFilter;
import com.manong.config.security.handler.AnonymousAuthenticationHandler;
import com.manong.config.security.handler.CustomerAccessDeniedHandler;
import com.manong.config.security.handler.LoginFailureHandler;
import com.manong.config.security.handler.LoginSuccessHandler;
import com.manong.config.security.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


/**
 * @ClassName SpringSecurityConfig
 * @Description: 创建SpringSecurityConfig配置类
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/19 11:08
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启权限注解控制
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomerUserDetailsService customerUserDetailsService;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;

    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Resource
    private CheckTokenFilter checkTokenFilter;

    /**
     * @Description: 注入加密处理类
     * @return org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     * @author zys
     * @date 2023/6/19 11:14
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * @Description: 处理登录认证
     * @param null
     * @return null
     * @author zys
     * @date 2023/6/19 11:15
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //登录前进行过滤
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //表单登录过滤
        http.formLogin()
                .loginProcessingUrl("/api/user/login") //登录请求的url地址，自定义
                .successHandler(loginSuccessHandler) //设置登录验证成功处理器
                .failureHandler(loginFailureHandler) //设置登录验证失败处理器
                .usernameParameter("username") //设置表单提交的用户名属性值，默认为username，如果为默认值可不设置此项
                .passwordParameter("password") //设置表单提交的密码属性值，默认为password，如果为默认值可不设置此项
                .and()
                .csrf().disable() // 禁用csrf防御机制
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //不创建session
                .and()
                .authorizeRequests() //设置拦截规则
                .antMatchers("/api/user/login").permitAll() //登录请求放行(不拦截)
                .anyRequest().authenticated() //其它一律请求都需要进行身份验证
                .and()
                .exceptionHandling() //身份认证异常处理规则
                .authenticationEntryPoint(anonymousAuthenticationHandler) //设置匿名无权限访问处理器
                .accessDeniedHandler(customerAccessDeniedHandler) //设置认证用户无权限访问处理器
                .and()
                .cors(); //开启跨域配置
    }

    /**
     * @Description: 配置认证处理器
     * @param null
     * @return null
     * @author zys
     * @date 2023/6/19 11:19
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
