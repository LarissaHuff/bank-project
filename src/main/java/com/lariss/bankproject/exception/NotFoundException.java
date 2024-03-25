package com.lariss.bankproject.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message + " not found.");
    }
}
