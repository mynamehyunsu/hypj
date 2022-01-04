package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="board_tbl")
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column
    private String userid;
    @Column
    private String subject;
    @Column
    private String content;
    @Column(columnDefinition = "date")
    private String regdate;
    @Column
    private String pwd;
    @Column
    private int count;

}
