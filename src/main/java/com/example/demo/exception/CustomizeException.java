package com.example.demo.exception;

import com.example.demo.enums.IResponseEnums;

/**
 * @ClassName CustomizeException
 * @Description TODO
 * @Author Halo
 **/
public class CustomizeException extends RuntimeException{

    IResponseEnums responseEnums;

    public CustomizeException(IResponseEnums enums) {
        this.responseEnums = enums;
    }
}
