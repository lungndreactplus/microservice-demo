package com.example.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public String stackTrace;

    public ApplicationException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        stackTrace = stackTraceToString(this);
    }

    public HttpStatusCode getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n\t\t");
        }
        return sb.toString();
    }
}
