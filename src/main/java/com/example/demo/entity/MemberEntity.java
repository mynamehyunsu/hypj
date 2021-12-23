package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="member_tbl")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {

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
}
