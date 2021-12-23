package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberrepo;



    //dto -> entity
    public void save(MemberDTO dto){
        //비밀번호 암호화 작업
        BCryptPasswordEncoder pwdencoder = new BCryptPasswordEncoder();
        dto.setPwd(pwdencoder.encode(dto.getPwd()));

        MemberEntity memberentity = MemberEntity.builder()
                .userid(dto.getUserid())
                .pwd(dto.getPwd())
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .zipcode(dto.getZipcode())
                .addr1(dto.getAddr1())
                .addr2(dto.getAddr2())
                .auth(dto.getAuth())
                .build();
        //암호화된 비번으로 DB에 저장
        memberrepo.save(memberentity);
    }

}
