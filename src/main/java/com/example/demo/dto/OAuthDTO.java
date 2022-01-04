package com.example.demo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class OAuthDTO extends User implements OAuth2User {

    private String userid;
    private String pwd;
    private String auth;
    private Map<String,Object> attr;


    //추가
    //OAuth2User는 MAP 타입으로 모든 인증 결과를  attribute라는 이름으로 가지고 있기때문에
    //UserInfo 역시 attr 라는 변수를 만들어주고 getATtribute()메소드를 오버라이드 한다
    public OAuthDTO(String username,
                    String password,
                    Collection<? extends GrantedAuthority> authorities,
                    Map<String, Object> attr) {
        super(username, password, authorities);
        this.userid = username;
        this.pwd = password;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return attr;
    }

    @Override
    public String getName() {
        return userid;
    }
}
