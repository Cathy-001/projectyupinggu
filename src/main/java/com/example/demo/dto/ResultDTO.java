package com.example.demo.dto;

import com.example.demo.enums.IResponseEnums;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResultDTO
 * @Description TODO
 * @Author Halo
 **/
@Data
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean success;
    private T data;
    private Integer code;
    private String message;

    public ResultDTO(boolean success, T data, IResponseEnums enums) {
        super();
        this.success = success;
        this.data = data;
        this.code = enums.getCode();
        this.message = enums.getMessage();
    }

    public ResultDTO(boolean success, Integer code, String message) {
        super();
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ResultDTO(boolean success, IResponseEnums enums) {
        super();
        this.success = success;
        this.code = enums.getCode();
        this.message = enums.getMessage();
    }

    public static <T> ResultDTO<T> successOf(T data, IResponseEnums responseEnums) {
        return new ResultDTO(true, data, responseEnums);
    }

    public static ResultDTO successOf(IResponseEnums responseEnums) {
        return new ResultDTO(true, true, responseEnums);
    }

    public static ResultDTO errorOf(IResponseEnums responseEnums) {
        return new ResultDTO(false, responseEnums);
    }

    public static ResultDTO errorOf(Integer code, String message) {
        return new ResultDTO(false, code, message);
    }
}
