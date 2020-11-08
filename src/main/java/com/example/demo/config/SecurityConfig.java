package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username, password, enabled FROM nhan_viens WHERE username = ?;")
//                .authoritiesByUsernameQuery("SELECT username ,concat('ROLE_',roles) as roles FROM nhan_viens WHERE username = ?;")
//                .passwordEncoder(encoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //khong can dang nhap van vao duoc
        http.authorizeRequests()
                .antMatchers("/css/**","/js/**","/img/**")
                .permitAll();
        //dang nhap moi vao xem duoc
        http.authorizeRequests()
                .antMatchers("/admin/**", "/user/**")
                .authenticated();
        //Chi user moi vao duoc
        http.authorizeRequests()
                .antMatchers("/user/**")
                .access("hasAnyRole('USER','ADMIN')");
        //tat ca cac duong dan co quyen admin moi xem duoc
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasRole('ADMIN')");
        //Xu ly khi nguoi dung vao trang khong co quyen de vao
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/");
        http.authorizeRequests()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login").permitAll()
                .defaultSuccessUrl("/thongbao/default")
                .failureUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
//        http.csrf().disable();
    }


}