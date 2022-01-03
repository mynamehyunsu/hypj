package com.example.demo.security;


import com.example.demo.service.MemberService;
import com.example.demo.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//시큐리티를사용한느장치다
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity//스프링 시큐리티 필터가 스프링 필터체인에 등록된다
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)// 컨트롤러에서쓰이는 secured 어노테이션 활성화
//각컨트롤러마다 시큐리티를 사용하고싶을떄(권한처리)
//컨트롤러 매핑 위에 @secured("권한명") 해주면 권한에맞는 계정만갈수잇음(한개만)
//prePostEnabled = true 이거는 여러권한 접근허용할때 컨트롤러 매핑위에@PreAuthorize(권한명)
public class MemberConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final MemberService service;

//    @Autowired
//    private final OAuthService oauthservice;


    //해당 매서드에 리턴되는 오브젝트를 IoC로 등록해준다
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
                //.anyRequest().authenticated()//나머지요청들은 권한의종류와 상관없이 권한이있어야접근가능
                .anyRequest().permitAll()//나머지는 인증없이 접근가능
                .and()
                .formLogin()
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


        //oauth 인증추가
        http.oauth2Login()
                .loginPage("/login.do")
                .defaultSuccessUrl("/index");
//                        .userInfoEndpoint()
//                                .userService(oauthservice);
        http.csrf().disable();


    }
}
