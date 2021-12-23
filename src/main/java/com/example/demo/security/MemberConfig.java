package com.example.demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//시큐리티를사용한느장치다
public class MemberConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //인증안받아도 접근가능
//                .antMatchers("/index").permitAll()//(메인페이지)
//                .antMatchers("/login.do","/agreeJoin.do","/memberFind.do").permitAll()//(로그인)
//                .antMatchers("/join.do").permitAll()//(가입)
//                .antMatchers("/boardList.do").permitAll()//(게시판)
//                .antMatchers("/introduction.do").permitAll()//(서점 소개)
//                .antMatchers("/mainBookList.do","/bookRead.do").permitAll()//(책소개)
//                .antMatchers("/boardRead.do")
                .antMatchers("/admin.do").hasRole("ADMIN")//관리자만 접근가능
                .anyRequest().permitAll();//나머지는 인증없이 접근가능

        http.formLogin()
                .loginPage("/login.do")//내가만든 로그인페이지로 이동
                .defaultSuccessUrl("/index");
        http.csrf().disable();
    }
}
