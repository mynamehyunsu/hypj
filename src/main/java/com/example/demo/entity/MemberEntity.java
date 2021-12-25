package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

//시큐리티가 /loginForm.do 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다(Security ContextHolder)
//시큐리티는 같은 세션안에 독립되게 공간이 생성이되어서 시큐리티 세션공간에맞는 타입으로 맞춰야함
//Security Session > Authentication > UserDetails(MemberEntity)
//UserDetails 을 상속 받은 MemberEntity클래스

@Entity
@Table(name="member_tbl")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity implements UserDetails {

    @Id
    private String userid;
    @Column
    private String pwd;
    @Column
    private String username;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String zipcode;
    @Column
    private String addr1;
    @Column
    private String addr2;
    @Column
    private String auth;



    //해당 계정의 권한 리턴하는곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return auth;
            }
        });

        return roles;
    }

    public String getUserid(){
        return userid;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override//계정 만료됬는지
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //안잠겼냐
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override//패스워드 완료 됬냐
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //사용가능하냐
    public boolean isEnabled() {
        return true;
    }
}
