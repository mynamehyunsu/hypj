package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="boardReply_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int num;

    @Column(nullable = false,length = 200)//not null ,200자 까지
    private String content;

    @ManyToOne//하나의 게시물에 여러개의 답변이 달릴수있다
    @JoinColumn(name="boardNum")
    private BoardEntity boardentity;

    @ManyToOne//하나의 유저에 여러개의 답변이있다
    @JoinColumn(name="userid")
    private MemberEntity memberentity;

    @CreationTimestamp
    private Timestamp createDate;
}
