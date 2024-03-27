package com.lariss.bankproject.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
