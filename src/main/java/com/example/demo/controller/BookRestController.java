package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private String uploadPath = "C:\\upload";

    @Autowired
    private final BookService bookservice;

    @PostMapping("/bookPostProc.do")
    public ResponseDTO<Integer> bookPostProc(BookDTO dto,
                                    @RequestParam("file") MultipartFile file

    ) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename()) ;//파일원본이름
            String folderPath = makeFolder(); //파일저장할 폴더생성
            System.out.println("folderPath : " + folderPath);
            //UUID 값 생성(이름중복 방지용)
            String uuid = UUID.randomUUID().toString();
            String uuidFileName = uuid + "_" + fileName;
            //파일절대경로 저장
            String saveName=uploadPath+ File.separator+folderPath+File.separator+uuidFileName;
            //원본 파일경로(원본이지미에서 썸네일 이미지 생성하기 때문
            Path filePath = Paths.get(saveName);

            System.out.println("filePath : " + filePath);

            //파일 저장
            Files.write(filePath,file.getBytes());

            String sUuidFileName = "S_"+uuid+"_"+fileName;
            String ThumbnailSaveName = uploadPath+File.separator+folderPath+File.separator+sUuidFileName;

            //썸네일 파일 경로
            File ThumbnailFile = new File(ThumbnailSaveName);

            //썸네일 파일 저장하기(해당경로에 원본파일경로,썸네일파일경로,너비,높이//파일명은 객체형으로 바꿔서 전달해야함)
            Thumbnailator.createThumbnail(filePath.toFile(),ThumbnailFile,190,230);

            dto.setFilename(uuidFileName);
            dto.setSfilename(sUuidFileName);
            // \ 를 다시 / 로 바꿔서 등록
            dto.setFolderpath(folderPath.replace(File.separator,"/"));
            System.out.println("dto.toString() : " + dto.toString());
        }catch(Exception e ){
            e.printStackTrace();
        }
        bookservice.postBook(dto);

        return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);

    }

    private String makeFolder(){

        String imsi = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("날짜 : " + imsi);
        String folderPath = imsi.replace("/", File.separator);
        System.out.println("파일분리 : " + folderPath);

        //폴더생성
        File uploadPathFolder = new File(uploadPath,folderPath);
        if(!uploadPathFolder.exists()){
            uploadPathFolder.mkdirs();
        }
        return folderPath; //오늘날짜 yyyy\MM\dd
    }

}
