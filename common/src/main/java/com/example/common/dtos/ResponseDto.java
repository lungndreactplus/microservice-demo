package com.example.common.dtos;

import com.example.common.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    public int statusCode;
    public String message;
    public T data;
    public Paging paging;
    public String stackTrace;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public ResponseDto toPaging(int page, int pageSize, List<T> data, String stackTrace) {
        return this;
    }
}

@AllArgsConstructor
@NoArgsConstructor
class Paging{
    public int page;
    public int pageSize;
    public int totalPages;
}
