package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    @Autowired
    private final MemberRepository memberrepo;


    ////시큐리티 설정에서 loginProcessingUrl("/loginForm.do");
    //요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행됨

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

       Optional<MemberEntity> result = memberrepo.findByUserid(userid);
        if(result.isPresent()){
            //Security Session(내부에 Authentication(내부에 result.get())이들어감) > Authentication > UserDetails(MemberEntity)
            //System.out.println("받아온유저아이디 : " + userid);
            //System.out.println("아이디를 통해 가져온 계정"+result.get());
            //System.out.println("아이디를통해받아온계정아이디 : " +result.get().getUserid());
            //System.out.println("비밀번호 : " + result.get().getPwd());
            //세션에 정보들어감
//            System.out.println(result.get().getUserid());
//            System.out.println(result.get().getUsername());
//            System.out.println(result.get().getAddr1());
//            System.out.println(result.get().getAddr2());
//            System.out.println(result.get().getEmail());
//            System.out.println(result.get().getPhone());
//            System.out.println(result.get().getZipcode());

            return result.get();
        }else{
            throw new UsernameNotFoundException("Check userid~~");
        }
    }

    //아이디 중복확인
    public int idCheck(String userid){
        Optional<MemberEntity> result = memberrepo.findByUsername(userid);
        if(result.isPresent()){
            return 1;
        }else{
            return 2;
        }

    }

    public MemberEntity getMember(String userid){
       Optional<MemberEntity> member = memberrepo.findByUserid(userid);
        return member.get();

    }

    //dto -> entity
    public MemberEntity save(MemberDTO dto){
        //비밀번호 암호화 작업
        BCryptPasswordEncoder pwdencoder = new BCryptPasswordEncoder();
        dto.setPwd(pwdencoder.encode(dto.getPwd()));

        MemberEntity memberentity = MemberEntity.builder()
                .userid(dto.getUserid())
                .pwd(dto.getPwd())
                .username(dto.getUsername())
                .phone(dto.getPhone1()+dto.getPhone2()+dto.getPhone3())
                .email(dto.getEmail())
                .zipcode(dto.getZipcode())
                .addr1(dto.getAddr1())
                .addr2(dto.getAddr2())
                .auth(dto.getAuth().toString())
                .build();
        //암호화된 비번으로 DB에 저장
        MemberEntity entity = memberrepo.save(memberentity);
        return entity;
    }

    //회원정보 수정
    @Transactional(rollbackFor = Exception.class)
    public int update(MemberDTO dto){

        BCryptPasswordEncoder pwdencoder = new BCryptPasswordEncoder();
        Optional<MemberEntity> memberentity = memberrepo.findByUserid(dto.getUserid());

        if(pwdencoder.matches(dto.getPwd(),memberentity.get().getPwd())) {
            String phone = dto.getPhone1() + dto.getPhone2() + dto.getPhone3();
            memberentity.get().update(phone, dto.getEmail(), dto.getZipcode(), dto.getAddr1(), dto.getAddr2());

            return 1;
        }else{
            return 2;
        }

    }

    //회원탈퇴

    public int memberDelete(String username){

        //시큐리티 세션 authentication 객체 안의 getPrincipal 메소드호출해서 안에있는 세션아이디
        //를통해 로그인한 유저 가져온다
        Optional<MemberEntity> member = memberrepo.findByUsername(username);
        System.out.println("삭제 메소드실행"+member.get().getUsername());
        memberrepo.deleteById(member.get().getUserid());
        System.out.println("이름을 가지고 DB조회한결과 : "+memberrepo.findByUserid(username));
         if(memberrepo.findByUserid(username).isEmpty()){
             //SecurityContextHolder.clearContext();
             //SecurityContextHolder.getContext().setAuthentication(null);
             return 1;
         }else{
             return 2;
         }
    }


}
