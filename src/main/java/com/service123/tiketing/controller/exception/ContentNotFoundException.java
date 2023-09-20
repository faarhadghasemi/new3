package com.service123.tiketing.controller.exception;

public class ContentNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Content Not Found";
    }
}
