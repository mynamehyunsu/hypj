package com.example.demo.handler;


import com.example.demo.dto.ResponseDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=IllegalArgumentException.class)
    public ResponseDTO<String> handleArgumentException(IllegalArgumentException e){
        return new ResponseDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()+"asdassss");
    }
}
