package com.example.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseDto<T> {
    public int statusCode;
    public String message;
    public T data;
    public Paging paging;
    public String stackTrace;

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
