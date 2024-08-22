package com.example.common.filters;

import com.example.common.dtos.ResponseDto;
import com.example.common.exceptions.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionFilter {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        if (ex instanceof ApplicationException) {
            var appEx = (ApplicationException) ex;
            var body = new ResponseDto(appEx.getHttpStatus().value(), appEx.getMessage(), null, null, appEx.stackTrace);
            return new ResponseEntity<>(body, ((ApplicationException) ex).getHttpStatus());
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

