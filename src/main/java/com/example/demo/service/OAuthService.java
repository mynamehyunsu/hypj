package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
public class OAuthService extends DefaultOAuth2UserService {

    @Autowired
    private final MemberRepository memberrepo;

    @Autowired
    private final PasswordEncoder passwordencoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("--------oauth loader-----");
        log.info("userrequest :" + userRequest);
        log.info("clientname : " + userRequest.getClientRegistration().getClientName());
        log.info("clientid :" + userRequest.getClientRegistration().getClientId());
        log.info("추가로 볼수있는것getRegistrationId() : " + userRequest.getClientRegistration().getRegistrationId());
        log.info("추가로볼수잇는것getClientAuthenticationMethod() : " + userRequest.getClientRegistration().getClientAuthenticationMethod());


        OAuth2User oauth2user = super.loadUser(userRequest);
        //구글이메일과 아이디 저장된거 보는것
        log.info(oauth2user.getAttributes());
        log.info("email : "+oauth2user.getAttribute("email"));

        String clientName = userRequest.getClientRegistration().getClientName();//Google이나옴
        String idemail=null;//네이버로할수도있고 다음으로 할수도있어서 받아온것으로하기위함
        if(clientName.equals("Google")){
            idemail = oauth2user.getAttribute("email");
        }

        //DB에 저장
        MemberEntity memberentity = saveSocialMember(idemail);

        //DB로부터 받은 MemberEntity안의 권한 꺼내기
        //Role꺼내기(시큐리티에 맞게)
        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        SimpleGrantedAuthority tmp = new SimpleGrantedAuthority(memberentity.getAuth());
        set.add(tmp);
        log.info("권한 꺼내기성공");
        //DTO에 memberentity의 값들 넣어주기
        MemberDTO EntityToDTO = new MemberDTO(
            memberentity.getUserid(),
                memberentity.getPassword(),
                set,
                oauth2user.getAttributes()
        );
        log.info("dto에 DB값 넣어주기 성공");
        //dto를 리턴하는이유는 뷰에서 보여질때 아이디가 이상한숫자로 보여지기때문에
        //구글 이메일 (아이디를)받아와서 화면에 뿌려주기위하여
        //구글로 로그인하면 db에 저장되는데 DB안에 있는 아이디를 가져와 dto에 담고 뷰에 보여주는것
        //OAuth2User타입으로 반환형이 되있지만 dto가되는 이유는 dto에서 상속받았기때문
        return EntityToDTO;
    }

    //DB저장을 위한 메소드
    private MemberEntity saveSocialMember(String email){

        //기존에 동일한 이메일로 가입한 경우에는 조회만
        Optional<MemberEntity> result = memberrepo.findByUserid(email);
        log.info("이메일을 가지고 DB에 조회 실행 ");
        if(result.isPresent()){
            return result.get();
        }else {

            //DB에 없다면 회원추가 패스워드 1111 로 고정 아이디는 이메일로지정
            MemberEntity memberentity = MemberEntity.builder()
                    .userid(email)
                    .pwd(passwordencoder.encode("1111"))
                    .build();

            //권한은 유저로 고정삽입
            memberentity.addMemberRole("ROLE_USER");

            //DB저장
            memberrepo.save(memberentity);
            return memberentity;
        }


    }




}
