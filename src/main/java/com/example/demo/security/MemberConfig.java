package com.example.demo.security;


import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//시큐리티를사용한느장치다
public class MemberConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final MemberService service;

    @Bean
    PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }

    /* AuthenticationManager Bean 등록 */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 시큐리티가 대신 로그인해주는데 password를 가로채기 하기에
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화하여 DB에 있는 해쉬랑 비교 가능
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordencoder());
    }

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
                //.antMatchers("/main").hasRole("ADMIN")
                .antMatchers("/main").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers("/admin.do").hasRole("ADMIN")//관리자만 접근가능
                .anyRequest().permitAll();//나머지는 인증없이 접근가능

        http.formLogin()
                .loginPage("/login.do")//내가만든 로그인페이지로 이동
                //로그인에서 받아오는 userid를 loadUserByUsername 함수의 매개변수랑 매칭시켜주기위함
                .usernameParameter("userid")
                .passwordParameter("pwd")
                .loginProcessingUrl("/loginForm.do")
                .defaultSuccessUrl("/index")
                .failureUrl("/index")
                ;
        http.logout()
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true);
        http.csrf().disable();

    }
}
