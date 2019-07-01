package com.example.vltask19.config;

import com.example.vltask19.customEnum.AuthRoles;
import com.example.vltask19.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiajinhui
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)/*启用基于注解的安全配置*/
public class VlWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
        /*return NoOpPasswordEncoder.getInstance();*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*改为基于数据库的登录及授权*/
        auth.userDetailsService(userService);

      /*  *//*进行基于内存的登录及授权*//*
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
                .withUser("role1")
                .password("123456")
                .roles(AuthRoles.RoleManager.toString())
                *//*加密*//*
                .and()
                .withUser("role2")
                .password(new BCryptPasswordEncoder(10).encode("456789"))
                .roles(AuthRoles.RoleManager.toString())
                .and()
                .withUser("admin2")
                .password(new BCryptPasswordEncoder(10).encode("456789"))
                .roles(AuthRoles.Admin.toString())
                .and()
                .withUser("user2")
                .password(new BCryptPasswordEncoder(10).encode("456789"))
                .roles(AuthRoles.UserManager.toString())
        ;*/
        /*改为基于数据库的登录及授权*/
        /*auth.jdbcAuthentication();*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*统一路径访问权限控制*/
        http.authorizeRequests()
                /*基于角色控制*/
                .antMatchers("/log/**")
                .hasRole("ADMIN")
                /*基于任一角色控制*/
                .antMatchers("/user/**")
                .access("hasAnyRole('" + AuthRoles.Admin.toString() + "','" + AuthRoles.UserManager.toString() + "')")
                .antMatchers("/authority/**")
                .access("hasAnyRole('" + AuthRoles.Admin.toString() + "','" + AuthRoles.AuthorityManager.toString() + "')")
                .antMatchers("/role/**")
                .access("hasAnyRole('" + AuthRoles.Admin.toString() + "','" + AuthRoles.RoleManager.toString() + "')")
                /*除以上权限控制以外,仅需登录后即可访问*/
                .anyRequest()
                .authenticated()
                /*开启表单登录*/
                .and()
                .formLogin()
                /*方便Ajax和移动端调用登录接口*/
                .loginProcessingUrl("/login")
                /*显式指定前端输入的用户名和密码*/
                .usernameParameter("username")
                .passwordParameter("password")
                /*显式指定登录成功后的响应*/
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.setStatus(200);
                        PrintWriter out = httpServletResponse.getWriter();
                        Map<String, Object> map = new HashMap<>(2);
                        Object principal = authentication.getPrincipal();
                        map.put("msg", principal);
                        map.put("status", 200);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                /*显式指定登录失败后的响应*/
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.setStatus(401);
                        PrintWriter out = httpServletResponse.getWriter();
                        Map<String, Object> map = new HashMap<>(2);
                        map.put("status", 401);
                        //这里面有哪些,取决于Security内置的登录默认抛出哪些异常
                        //并且定义exception和handler处理这是一个值得参考的处理形式
                        if (e instanceof LockedException) {
                            map.put("msg", "账户被锁定,登录失败");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "账户名或密码输入错误,登录失败");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账户被禁用,登录失败");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账户已过期,登录失败");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "登录信息已过期,登录失败");
                        } else {
                            map.put("msg", "其它异常,登录失败");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                /*注销登录配置*/
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
                    }
                })
                /*显式指定注销登录成功后的反馈*/
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.sendRedirect("/login");
                    }
                })
                /*表示和登录相关的接口不需要认证,登录相关???如何识别???*/
                .permitAll()
                /*拦截CSRF:Cross Site Request Forgery*/
                .and()
                .csrf()
                .disable()
        ;
    }
}
