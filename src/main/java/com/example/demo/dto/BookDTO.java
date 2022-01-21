package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private String subject;
    private String content;
    private int price;
    private String filename;
    private String sfilename;
    private String folderpath;
    private int count;
}
