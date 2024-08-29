package com.sp.common.filters;

import com.sp.common.dtos.ResponseDto;
import com.sp.common.exceptions.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionFilter {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        if (ex instanceof ApplicationException appEx) {
            var body = new ResponseDto(appEx.getHttpStatus().value(), appEx.getMessage(), null, null, appEx.stackTrace);
            return new ResponseEntity<>(body, appEx.getHttpStatus());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

