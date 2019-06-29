package com.example.vltask19.config;

import com.example.vltask19.customEnum.AuthRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class VLWebSercurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*进行基于内存的登录及授权*/
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("123456")
                .roles(AuthRoles.Admin.toString())
                .and()
                .withUser("auth")
                .password("123456")
                .roles(AuthRoles.AuthorityManager.toString())
                .and()
                .withUser("user")
                .password("123456")
                .roles(AuthRoles.UserManager.toString())
                .and()
                .withUser("role")
                .password("123456")
                .roles(AuthRoles.RoleManager.toString());
        /*TODO 改为基于数据库的登录及授权*/
        /*auth.jdbcAuthentication();*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /*基于角色控制*/
                .antMatchers("/log/**")
                .hasRole("ADMIN")
                /*基于任一角色控制*/
                .antMatchers("/user/**")
                .access("hasAnyRole('"+AuthRoles.Admin.toString()+"','"+AuthRoles.UserManager.toString()+"')")
                .antMatchers("/authority/**")
                .access("hasAnyRole('"+AuthRoles.Admin.toString()+"','"+AuthRoles.AuthorityManager.toString()+"')")
                .antMatchers("/role/**")
                .access("hasAnyRole('"+AuthRoles.Admin.toString()+"','"+AuthRoles.RoleManager.toString()+"')")
                /*除以上权限控制以外,仅需登录后即可访问*/
                .anyRequest()
                .authenticated()
                /*开启表单登录*/
                .and()
                .formLogin()
                /*方便Ajax和移动端调用登录接口*/
                .loginProcessingUrl("/login")
                /*表示和登录相关的接口不需要认证,登录相关???如何识别???*/
                .permitAll()
                /*拦截CSRF:Cross Site Request Forgery*/
                .and()
                .csrf()
                .disable()
        ;
    }
}
