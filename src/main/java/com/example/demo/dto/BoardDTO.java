package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private Long num;
    private String userid;
    private String subject;
    private String content;
    private String regdate;
    private String pwd;
    private int count;
}
