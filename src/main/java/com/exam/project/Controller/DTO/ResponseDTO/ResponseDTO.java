package com.exam.project.Controller.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO<T> {
    private Boolean success;
    private T data;
    private Error error;

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(true,data,null);
    }

    public static <T> ResponseDTO<T> fail(String code, String message) {
        return new ResponseDTO<>(false,null,new Error(code,message));
    }

    @Getter
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;
    }

}
