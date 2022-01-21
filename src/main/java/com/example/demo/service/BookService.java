package com.example.demo.service;


import com.example.demo.dto.BookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookrepo;

    public BookEntity postBook(BookDTO dto){
        BookEntity bookentity = BookEntity.builder()
                .subject(dto.getSubject())
                .content(dto.getContent())
                .price(dto.getPrice())
                .filename(dto.getFilename())
                .sfilename(dto.getSfilename())
                .folderPath(dto.getFolderpath())
                .build();
        BookEntity book =bookrepo.save(bookentity);
        return book;
    }
}
